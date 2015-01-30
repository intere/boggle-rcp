package com.intere.rcp.boggle.core.interfaces;

import com.intere.rcp.boggle.core.model.Player;

/**
 * The Computer Player interface.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IComputerPlayer {

    /**
     * Accessor for the Client.
     * 
     * @return
     */
    public IBoggleClient getClient();

    /**
     * Accessor for the player.
     * 
     * @return
     */
    public Player getPlayer();

    /**
     * Login to the server.
     */
    public void login();

    /**
     * The difficulty utilized by the Computer Player.
     * @return
     */
    public String getDifficulty();

    /**
     * The strategy utilized by the Computer Player.
     * 
     * @return
     */
    public String getStrategy();

    /**
     * This method will make the computer player join your game.
     * 
     * @param gameId
     */
    public void joinGame(String gameId);
}
