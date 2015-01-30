package com.intere.rcp.boggle.core.managers;

import java.util.List;

import com.intere.rcp.boggle.core.interfaces.IBoggleClient;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Observable;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.core.model.Stat;

/**
 * This class is just a delegate that delegates all client calls to all clients.  A Client <u>must</u> register
 * with this class to receive client notifications.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ClientDelegateService extends Observable<IBoggleClient> implements IBoggleClient {
    
    /** The singleton instance variable.  This is the instance of the ClientDelegateService that everyone gets.  */
    private static ClientDelegateService instance;

    /** private constructor - no external instantiation.  */
    private ClientDelegateService() {
        // TODO - any instantiation
    }

    /**
     * Singleton accessor method.
     * @return The ClientDelegateService instance.
     */
    public static synchronized ClientDelegateService getInstance() {
        if (instance == null) {
            instance = new ClientDelegateService();
        }
        return instance;
    }

    public void gameCreated(Game game) {
        for (IBoggleClient client : getListeners()) {
            client.gameCreated(game);
        }
    }

    public void gameDestroyed(String gameId) {
        for (IBoggleClient client : getListeners()) {
            client.gameDestroyed(gameId);
        }
    }

    public void gameEnded(String gameId) {
        for (IBoggleClient client : getListeners()) {
            client.gameEnded(gameId);
        }
    }

    public void gameStarted(String gameId, String gameBoard, int timeInSec) {
        for (IBoggleClient client : getListeners()) {
            client.gameStarted(gameId, gameBoard, timeInSec);
        }
    }

    public void playerJoined(String gameId, String userId) {
        for (IBoggleClient client : getListeners()) {
            client.playerJoined(gameId, userId);
        }
    }

    public void playerLeft(String gameId, String userId) {
        for (IBoggleClient client : getListeners()) {
            client.playerLeft(gameId, userId);
        }
    }

    public void playerLoggedIn(Player player) {
        for (IBoggleClient client : getListeners()) {
            client.playerLoggedIn(player);
        }
    }

    public void playerLoggedOut(String userId) {
        for (IBoggleClient client : getListeners()) {
            client.playerLoggedOut(userId);
        }
    }

    public void receivedGameList(List<Game> games) {
        for (IBoggleClient client : getListeners()) {
            client.receivedGameList(games);
        }
    }

    public void receivedGameStats(Stat stats) {
        for (IBoggleClient client : getListeners()) {
            client.receivedGameStats(stats);
        }
    }

    public void receivedUserList(List<Player> players) {
        for (IBoggleClient client : getListeners()) {
            client.receivedUserList(players);
        }
    }

    public void scoreUpdated(String gameId, List<Score> scores) {
        for (IBoggleClient client : getListeners()) {
            client.scoreUpdated(gameId, scores);
        }
    }

    public void timeUpdated(String gameId, int timeInSec) {
        for (IBoggleClient client : getListeners()) {
            client.timeUpdated(gameId, timeInSec);
        }
    }

    public void wordValidated(String gameId, String userId, String word, int score) {
        for (IBoggleClient client : getListeners()) {
            client.wordValidated(gameId, userId, word, score);
        }
    }
}
