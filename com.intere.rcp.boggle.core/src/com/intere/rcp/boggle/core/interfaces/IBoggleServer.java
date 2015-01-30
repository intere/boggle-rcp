package com.intere.rcp.boggle.core.interfaces;

import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;

/**
 * This is the interface that a Boggle Server must implement in order to
 * interact with the Boggle Client. All messages are asynchronous. Some messages
 * have a response, others do not.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IBoggleServer {

    /**
     * This is the method that logs a player in to the server.
     * 
     * @param player
     */
    public void login(Player player);

    /**
     * This method is the method that logs a player out of the server.
     * 
     * @param userId
     */
    public void logout(String userId);

    /**
     * This method tells the server that the provided user would like the server
     * to send the user list.
     */
    public void requestUserList(String userId);

    /**
     * This method tells the server that the provided user would like the server
     * to send the Game List.
     */
    public void requestGameList(String userId);

    /**
     * This method tells the server that the provided user would like to create
     * the provided Game.
     * 
     * @param userId
     * @param game
     */
    public void createGame(String userId, Game game);

    /**
     * This method tells the server that the provided user would like to destroy
     * the provided game.
     * 
     * @param userId
     * @param gameId
     */
    public void destroyGame(String userId, String gameId);

    /**
     * This method tells the server that the provided user would like to join
     * the provided game.
     * 
     * @param userId
     * @param gameId
     */
    public void joinGame(String userId, String gameId);

    /**
     * This method tells the server that the provided user would like to leave
     * the provided game.
     * 
     * @param userId
     * @param gameId
     */
    public void leaveGame(String userId, String gameId);

    /**
     * This method tells the server that the provided user would like to guess
     * the provided word against the provided game.
     * 
     * @param userId
     * @param gameId
     * @param word
     */
    public void guessWord(String userId, String gameId, String word);

    /**
     * This method tells the server that the provided user would like to be told
     * how much time is left in the provided game.
     * 
     * @param userId
     * @param gameId
     */
    public void getTime(String userId, String gameId);

}
