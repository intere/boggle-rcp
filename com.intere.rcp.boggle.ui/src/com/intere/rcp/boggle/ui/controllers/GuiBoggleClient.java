package com.intere.rcp.boggle.ui.controllers;

import java.util.List;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.intere.rcp.boggle.core.BoggleCorePlugin;
import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.core.interfaces.IBoggleClient;
import com.intere.rcp.boggle.core.managers.ClientDelegateService;
import com.intere.rcp.boggle.core.managers.ServerDelegateService;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.core.model.Stat;
import com.intere.rcp.boggle.core.model.Game.GameState;
import com.intere.rcp.boggle.ui.handlers.DefaultPerspectiveHandler;
import com.intere.rcp.boggle.ui.perspectives.BoggleGamePerspective;
import com.intere.rcp.boggle.ui.perspectives.DefaultPerspective;
import com.intere.rcp.boggle.ui.perspectives.LoginPerspective;
import com.intere.rcp.boggle.ui.views.BoggleBoardView;
import com.intere.rcp.boggle.ui.views.ScoreView;
import com.intere.rcp.boggle.ui.views.StatView;
import com.intere.rcp.boggle.ui.views.TimerView;

/**
 * This class is the Boggle Client Implementation for the GUI.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class GuiBoggleClient implements IBoggleClient {

    /**
     * The singleton instance variable. This is the instance of the
     * GuiBoggleClient that everyone gets.
     */
    private static GuiBoggleClient instance;

    /** private constructor - no external instantiation. */
    private GuiBoggleClient() {
        ClientDelegateService.getInstance().addListener(this);
    }

    /**
     * Logs into the registered server.
     */
    public void login() {
        ServerDelegateService.getInstance().login(PlayerManager.getInstance().getPlayer());
        showPerspective(DefaultPerspective.ID);
    }

    /**
     * Logs out of the registered server.
     */
    public void logout() {
        ServerDelegateService.getInstance().logout(PlayerManager.getInstance().getPlayer().getPlayerId());
        showPerspective(LoginPerspective.ID);
    }

    /**
     * Singleton accessor method.
     * 
     * @return The GuiBoggleClient instance.
     */
    public static synchronized GuiBoggleClient getInstance() {
        if (instance == null) {
            instance = new GuiBoggleClient();
        }
        return instance;
    }

    public void gameCreated(Game game) {
        GameManager.getInstance().addGame(game);
    }

    public void gameDestroyed(String gameId) {
        GameManager.getInstance().removeGame(gameId);
    }

    public void gameEnded(String gameId) {
        GameManager.getInstance().getGame(gameId).setGameState(GameState.Ended);
        GameManager.getInstance().alertModelEvent();

        DefaultPerspectiveHandler.enablePerspective();
    }

    public void gameStarted(final String gameId, final String gameBoard, int timeInSec) {
        PlayerManager.getInstance().getPlayer().clearWordList();
        GameManager.getInstance().getGame(gameId).setGameState(GameState.InProgress);
        GameManager.getInstance().alertModelEvent();
        
        showPerspective(BoggleGamePerspective.ID);
        showView(ScoreView.ID);
        showView(TimerView.ID);
        showView(BoggleBoardView.ID);
        
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                ScoreView sv = (ScoreView) getView(ScoreView.ID);
                sv.setGameId(gameId);
                
                BoggleBoardView bbv = (BoggleBoardView) getView(BoggleBoardView.ID);
                bbv.setGameId(gameId);
                
                TimerView tv = (TimerView) getView(TimerView.ID);
                tv.setGameId(gameId);
            }
        });
    }

    protected IPerspectiveDescriptor getGamePerspective() {
        return PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("com.intere.rcp.boggle.ui.perspectives.BoggleGamePerspective");
    }

    public void playerJoined(String gameId, String userId) {
        GameManager.getInstance().getGame(gameId).getPlayerList().add(PlayerManager.getInstance().getPlayer(userId));
        GameManager.getInstance().alertModelEvent();
    }

    public void playerLeft(String gameId, String userId) {
        GameManager.getInstance().getGame(gameId).getPlayerList().remove(PlayerManager.getInstance().getPlayer(userId));
        GameManager.getInstance().alertModelEvent();
    }

    public void playerLoggedIn(Player player) {
        PlayerManager.getInstance().addPlayer(player);
    }

    public void playerLoggedOut(String userId) {
        PlayerManager.getInstance().removePlayer(userId);
    }

    public void receivedGameList(List<Game> games) {
        GameManager.getInstance().clearGames();
        GameManager.getInstance().addAllGames(games);
    }

    /**
     * Handle receiving the Game Stats.
     */
    public void receivedGameStats(Stat stats) {
        final Game game = GameManager.getInstance().getGame(stats.getGameId());
        game.setStats(stats);
        showView(StatView.ID);

        
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                StatView v = (StatView) getView(StatView.ID);
                v.setGameId(game.getGameId());
            }
        });
    }

    public void receivedUserList(List<Player> players) {
        PlayerManager.getInstance().clearPlayers();
        PlayerManager.getInstance().addAllPlayers(players);
    }

    public void scoreUpdated(String gameId, List<Score> scores) {
        Game g = GameManager.getInstance().getGame(gameId);

        for (Score s : scores) {
            g.getScore(s.getUserId()).setScore(s.getScore());
        }

        GameManager.getInstance().alertModelEvent(ModelEvent.createModelUpdatedEvent(this, g));
    }

    public void timeUpdated(String gameId, int timeInSec) {
        GameManager.getInstance().getGame(gameId).setTime(timeInSec);
        GameManager.getInstance().alertModelEvent();
    }

    public void wordValidated(String gameId, String userId, String word, int points) {
        if (userId.equals(PlayerManager.getInstance().getPlayer().getPlayerId())) {
            PlayerManager.getInstance().getPlayer().addWord(word, points);
        }
    }

    /**
     * This method will show a view for you (and it creates a new Runnable to do
     * it):
     * 
     * @param id
     */
    protected void showView(final String id) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(id);
                } catch (PartInitException e) {
                    BoggleCorePlugin.getDefault().logErrorMessage("Error trying to show the View: " + id, e);
                }
            }
        });
    }

    /**
     * Helper method to show a perspective (inside a new runnable).
     * 
     * @param id
     */
    protected void showPerspective(final String id) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(id));
            }
        });
    }

    /**
     * Convenience method to get the instance of the View by it's part.
     * 
     * @param id
     * @return
     */
    protected IViewPart getView(String id) {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(id);
    }

}
