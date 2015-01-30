package com.intere.rcp.boggle.ui.controllers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.core.model.ObservableModel;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;

/**
 * This class is responsible for listening to updates to the players.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class PlayerManager extends ObservableModel implements IModelListener {
    /**
     * The singleton instance variable. This is the instance of the
     * PlayerManager that everyone gets.
     */
    private static PlayerManager instance;
    
    /** The data structure that holds the players.  */
    private PairedList<String, Player> players = new PairedList<String, Player>();

    /** The player who is using this client.  */
    private Player player;
    
    /** Keeps track of whether or not the user is logged in.  */
    private boolean loggedIn;

    /** private constructor - no external instantiation. */
    private PlayerManager() {
        players.addModelListener(this);
    }

    /**
     * Singleton accessor method.
     * 
     * @return The PlayerManager instance.
     */
    public static synchronized PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * Adds a player to the Player List.
     * 
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player.getPlayerId(), player);
    }

    /**
     * Removes a player from the Player List.
     * 
     * @param player
     */
    public void removePlayer(String uid) {
        players.remove(uid);
    }

    /**
     * Clears the player list.
     */
    public void clearPlayers() {
        players.clear();
    }

    /**
     * Adds all of the provided players to the list.
     * 
     * @param players
     */
    public void addAllPlayers(List<Player> players) {
        boolean changed = false;
        
        for (Player player : players) {
            this.players.add(player.getPlayerId(), player);
        }
    }
    
    public void onModelEvent(ModelEvent event) {
        switch (event.getEventType()) {
        case DataAdded: {
            alertModelEvent(ModelEvent.createDataAddedEvent(this, event.getModel(), event.getData()));
            break;
        }

        case DataRemoved: {
            alertModelEvent(ModelEvent.createDataRemovedEvent(this, event.getModel(), event.getData()));
            break;
        }

        case ModelUpdated: {
            alertModelEvent(ModelEvent.createModelUpdatedEvent(this, event.getModel()));
            break;
        }
        }
    }

    /**
     * Get the list of players.
     * 
     * @return
     */
    public List<Player> getPlayerList() {
        return players.getList();
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public boolean isLoggedIn() {
        return getPlayerList().contains(getPlayer());
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Player getPlayer(String userId) {
        return players.get(userId);
    }
}
