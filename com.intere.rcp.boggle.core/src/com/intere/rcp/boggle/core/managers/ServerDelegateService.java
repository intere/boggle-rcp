package com.intere.rcp.boggle.core.managers;

import com.intere.rcp.boggle.core.interfaces.IBoggleServer;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;

public class ServerDelegateService implements IBoggleServer {
    
    /** The singleton instance variable.  This is the instance of the ServerDelegateService that everyone gets.  */
    private static ServerDelegateService instance;
    
    private IBoggleServer server;

    /** private constructor - no external instantiation.  */
    private ServerDelegateService() {
        // TODO - any instantiation
    }

    /**
     * Singleton accessor method.
     * @return The ServerDelegateService instance.
     */
    public static synchronized ServerDelegateService getInstance() {
        if (instance == null) {
            instance = new ServerDelegateService();
        }
        return instance;
    }
    
    public IBoggleServer getServer() {
        return server;
    }
    
    public void setServer(IBoggleServer server) {
        this.server = server;
    }    

    public void createGame(String userId, Game game) {
        getServer().createGame(userId, game);
    }

    public void destroyGame(String userId, String gameId) {
        getServer().destroyGame(userId, gameId);
    }

    public void getTime(String userId, String gameId) {
        getServer().getTime(userId, gameId);
    }

    public void guessWord(String userId, String gameId, String word) {
        getServer().guessWord(userId, gameId, word);
    }

    public void joinGame(String userId, String gameId) {
        getServer().joinGame(userId, gameId);
    }

    public void leaveGame(String userId, String gameId) {
        getServer().leaveGame(userId, gameId);
    }

    public void login(Player player) {
        getServer().login(player);
    }

    public void logout(String userId) {
        getServer().logout(userId);
    }

    public void requestGameList(String userId) {
        getServer().requestGameList(userId);
    }

    public void requestUserList(String userId) {
        getServer().requestUserList(userId);
    }

}
