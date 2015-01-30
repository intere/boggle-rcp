package com.intere.rcp.boggle.computer.common;

import java.util.List;

import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.core.interfaces.IBoggleClient;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Score;
import com.intere.rcp.boggle.core.model.Stat;

public class ComputerClient implements IBoggleClient {
    
    private AbstractComputerClient computer;
    
    public void setComputer(AbstractComputerClient computer) {
        this.computer = computer;
    }
    
    public AbstractComputerClient getComputer() {
        return computer;
    }
    
    public void gameCreated(Game game) {
        // TODO Auto-generated method stub
    }

    public void gameDestroyed(String gameId) {
        // TODO Auto-generated method stub
    }

    public void gameEnded(String gameId) {
        if(gameId.equals(computer.getGame().getGameId())) {
            computer.endGame();
        }
    }

    public void gameStarted(String gameId, String gameBoard, int timeInSec) {
        if(computer.getGame()==null) {
            computer.setGame(GameManager.getInstance().getGame(gameId));
        }
        if(gameId.equals(computer.getGame().getGameId())) {
            computer.getGame().setBoard(gameBoard);
            computer.startGame();
        }
    }

    public void playerJoined(String gameId, String userId) {
        // TODO Auto-generated method stub

    }

    public void playerLeft(String gameId, String userId) {
        // TODO Auto-generated method stub

    }

    public void playerLoggedIn(Player player) {
        // TODO Auto-generated method stub

    }

    public void playerLoggedOut(String userId) {
        // TODO Auto-generated method stub

    }

    public void receivedGameList(List<Game> games) {
        // TODO Auto-generated method stub

    }

    public void receivedGameStats(Stat stats) {
        // TODO Auto-generated method stub

    }

    public void receivedUserList(List<Player> players) {
        // TODO Auto-generated method stub

    }

    public void scoreUpdated(String gameId, List<Score> scores) {
        // TODO Auto-generated method stub

    }

    public void timeUpdated(String gameId, int timeInSec) {
        // TODO Auto-generated method stub

    }

    public void wordValidated(String gameId, String userId, String word, int score) {
        // TODO
    }

}
