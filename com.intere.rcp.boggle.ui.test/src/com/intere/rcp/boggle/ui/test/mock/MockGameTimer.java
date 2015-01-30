package com.intere.rcp.boggle.ui.test.mock;

import com.intere.rcp.boggle.core.managers.ClientDelegateService;
import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.Stat;

/**
 * This class is used by the mock Game Service to inform the client of timer
 * updates.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class MockGameTimer extends Thread {
    private Game game;
    
    public MockGameTimer(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        while(game.getTime()>0)
        {
            game.setTime(game.getTime()-1);
            ClientDelegateService.getInstance().timeUpdated(game.getGameId(), game.getTime());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {                
                e.printStackTrace();
            }
        }
        
        ClientDelegateService.getInstance().gameEnded(game.getGameId());
        ClientDelegateService.getInstance().receivedGameStats(Stat.fromGame(game));
    }
}
