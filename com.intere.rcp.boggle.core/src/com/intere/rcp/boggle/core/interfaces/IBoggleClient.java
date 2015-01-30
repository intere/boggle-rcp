package com.intere.rcp.boggle.core.interfaces;

import java.util.List;

import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.core.model.Stat;

/**
 * This is the interface that a Boggle Client must implement in order to
 * interact with the Boggle Server. All messages are asynchronous. Some messages
 * have a response, others do not.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IBoggleClient {
	// TODO - non-game creator should "confirm" start of game.
	
    /**
     * Informs the client that a List of Players (users) has arrived. This is
     * the "global" list of users, not specific to a game.
     * 
     * @param players
     */
    public void receivedUserList(List<Player> players);

    /**
     * Informs the client that a list of games has arrived. The states can be
     * varying.
     * 
     * @param games
     */
    public void receivedGameList(List<Game> games);

    /**
     * Informs the client that a game has started. This provides the user the
     * board.
     * 
     * @param gameId
     * @param gameBoard
     * @param timeInSec
     */
    public void gameStarted(String gameId, String gameBoard, int timeInSec);

    /**
     * Informs the client that the provided game has ended.
     * 
     * @param gameId
     */
    public void gameEnded(String gameId);

    /**
     * Informs the client that the game has a score update.
     * 
     * @param gameId
     * @param scores
     */
    public void scoreUpdated(String gameId, List<Score> scores);

    /**
     * Informs the client that a player has joined the game.
     * 
     * @param gameId
     * @param userId
     */
    public void playerJoined(String gameId, String userId);

    /**
     * Informs the client that a player has left the game.
     * 
     * @param gameId
     * @param userId
     */
    public void playerLeft(String gameId, String userId);

    /**
     * Informs the client that a player has logged in.
     * 
     * @param player
     */
    public void playerLoggedIn(Player player);

    /**
     * Informs the client that a player has logged out.
     * 
     * @param userId
     */
    public void playerLoggedOut(String userId);

    /**
     * Informs the client that a game has been created.
     * 
     * @param game
     */
    public void gameCreated(Game game);

    /**
     * Informs the client that a game has been destroyed.
     * 
     * @param gameId
     */
    public void gameDestroyed(String gameId);

    /**
     * Informs the client that a word has been validated.
     * 
     * @param gameId
     * @param userId
     * @param word
     */
    public void wordValidated(String gameId, String userId, String word, int score);

    /**
     * Informs the client that the time has been updated.
     * 
     * @param gameId
     * @param timeInSec
     */
    public void timeUpdated(String gameId, int timeInSec);

    /**
     * Informs the client that the game statistics have been received.
     * 
     * @param gameId
     * @param stats
     */
    public void receivedGameStats(Stat stats);
}
