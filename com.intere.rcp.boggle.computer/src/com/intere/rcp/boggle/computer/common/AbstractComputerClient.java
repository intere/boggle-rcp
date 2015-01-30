package com.intere.rcp.boggle.computer.common;

import com.intere.rcp.boggle.core.interfaces.IBoggleClient;
import com.intere.rcp.boggle.core.interfaces.IComputerPlayer;
import com.intere.rcp.boggle.core.managers.ClientDelegateService;
import com.intere.rcp.boggle.core.managers.ServerDelegateService;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.descriptors.ComputerPlayerDescriptor;

/**
 * The abstract implementation of the Computer Player.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public abstract class AbstractComputerClient extends Player implements IComputerPlayer {

    /** The game that the computer player is playing.  */
    private Game game;
    
    /** Boggle Client for this computer player.  */
    private ComputerClient client;
    
    /** The difficulty of this computer player.  */
    private String difficulty;
    
    /** The strategy utilized by this computer player.  */
    private String strategy;
    
    /** The word list for the player. */
    private PairedList<String, Integer> wordList = new PairedList<String, Integer>();
    
    /** The Slave Thread.  */
    
    /**
     * Default constructor; initializes the private member data.
     */
    public AbstractComputerClient() {
        client = new ComputerClient();
        client.setComputer(this);
        ClientDelegateService.getInstance().addListener(client);
    }
    
    /**
     * Set the member data from the provided {@link ComputerPlayerDescriptor}.
     * @param desc
     */
    public void fromDescriptor(ComputerPlayerDescriptor desc)
    {
        this.difficulty = desc.getDifficulty();
        this.strategy = desc.getStrategy();
        setUsername(desc.getName());
    }

    public IBoggleClient getClient() {
        return client;
    }    

    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return this;
    }
    
    public String getStrategy() {
        return strategy;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void login() {
        ServerDelegateService.getInstance().login(getPlayer());
    }
    
    public void joinGame(String gameId) {
        // TODO - WTF?
    }
    
    public PairedList<String, Integer> getWordList() {
        return wordList;
    }

    /**
     * Joins the game.
     */
    public abstract void startGame();
    
    /**
     * Ends the game.
     */
    public abstract void endGame();
    
    /**
     * 
     */
    public abstract void guessWord();

    
}