package com.intere.rcp.boggle.ui.test.mock;

import java.io.IOException;

import com.intere.rcp.boggle.core.interfaces.IBoggleServer;
import com.intere.rcp.boggle.core.managers.ClientDelegateService;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.core.model.ScoreSet;
import com.intere.rcp.boggle.server.util.BoggleDiceRoller;

/**
 * Mock implementation of a boggle server.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class MockServer implements IBoggleServer {
    public static final String SCORE_SET = "/resources/score-scheme-1.props";

    /** Data structure of games. */
    private MockGameDelegate games = new MockGameDelegate();

    /** Data structure of players. */
    private PairedList<String, Player> players = new PairedList<String, Player>();

    /**
     * Create the game.
     */
    public void createGame(String userId, Game game) {
        games.add(game.getGameId(), game);
        ClientDelegateService.getInstance().gameCreated(game);
        game.setBoard(new BoggleDiceRoller(game.getGameType()).roll());
        try {
            game.setScoringSet(ScoreSet.fromBundleFile(SCORE_SET));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientDelegateService.getInstance().gameStarted(game.getGameId(), game.getBoard(), game.getTime());
        createScores(game);
        ClientDelegateService.getInstance().scoreUpdated(game.getGameId(), game.getScores());
        MockGameTimer timer = new MockGameTimer(game);
        timer.start();
    }

    /**
     * Remove the game.
     */
    public void destroyGame(String userId, String gameId) {
        games.remove(gameId);
        ClientDelegateService.getInstance().gameDestroyed(gameId);
    }

    /**
     * Get the game time.
     */
    public void getTime(String userId, String gameId) {
        ClientDelegateService.getInstance().timeUpdated(gameId, games.get(gameId).getTime());
    }

    /**
     * User (who's userId correlates to the provided userId) has guessed a on
     * the provided gameId.
     */
    public void guessWord(String userId, String gameId, String word) {
        if (games.get(gameId).guessWord(userId, word)) {

            // Track the scores (per game/player):
            int score = games.get(gameId).getScoringSet().scoreWord(word);
            games.get(gameId).getPlayers().get(userId).addWord(word, score);
            
            // notify the client:
            ClientDelegateService.getInstance().wordValidated(gameId, userId, word, score);
            ClientDelegateService.getInstance().scoreUpdated(gameId, games.get(gameId).getScores());
        }
    }
    
    
    public void joinGame(String userId, String gameId) {
        // TODO Auto-generated method stub

    }

    public void leaveGame(String userId, String gameId) {
        // TODO Auto-generated method stub

    }

    /**
     * Log a player in.
     */
    public void login(Player player) {
        players.add(player.getPlayerId(), player);
        ClientDelegateService.getInstance().playerLoggedIn(player);
    }

    /**
     * Log a player out.
     */
    public void logout(String userId) {
        players.remove(userId);
        ClientDelegateService.getInstance().playerLoggedOut(userId);
    }

    /**
     * User has requested a game list.
     */
    public void requestGameList(String userId) {
        ClientDelegateService.getInstance().receivedGameList(games.getList());
    }

    /**
     * User has requested a user list.
     */
    public void requestUserList(String userId) {
        ClientDelegateService.getInstance().receivedUserList(players.getList());
    }
    
    /**
     * 
     * @param game
     */
    protected void createScores(Game game) {
        for(Player p : game.getPlayerList()) {
            Score s = game.getScore(p.getPlayerId());
            s.setScore(0);
        }
    }

}
