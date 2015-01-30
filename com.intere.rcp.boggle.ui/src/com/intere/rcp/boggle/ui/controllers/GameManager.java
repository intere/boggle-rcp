package com.intere.rcp.boggle.ui.controllers;

import java.util.List;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.ObservableModel;
import com.intere.rcp.boggle.core.model.PairedList;

/**
 * This class is responsible for listening to updates to the games.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class GameManager extends ObservableModel implements IModelListener {
    /**
     * The singleton instance variable. This is the instance of the GameManager
     * that everyone gets.
     */
    private static GameManager instance;

    PairedList<String, Game> games = new PairedList<String, Game>();

    private boolean dirty;

    /** private constructor - no external instantiation. */
    private GameManager() {
        games.addModelListener(this);
    }

    /**
     * Singleton accessor method.
     * 
     * @return The GameManager instance.
     */
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Adds a player to the player list.
     * 
     * @param game
     */
    public void addGame(Game game) {
        games.add(game.getGameId(), game);
    }

    /**
     * Removes a game from the game list.
     * 
     * @param uid
     */
    public void removeGame(String uid) {
        Game game = games.remove(uid);
    }

    /**
     * Clears the game list.
     */
    public void clearGames() {
        games.clear();
    }

    /**
     * Adds all of the provided games to the list.
     * 
     * @param games
     */
    public void addAllGames(List<Game> games) {
        for (Game game : games) {
            this.games.add(game.getGameId(), game);
        }
    }

    public List<Game> getGameList() {
        return games.getList();
    }

    /**
     * Handles the model events that propagate from the games data structure.
     */
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

        setDirty(true);
    }
    
    
    public void alertModelEvent() {
        alertModelEvent(ModelEvent.createModelUpdatedEvent(this, games.getList()));
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
