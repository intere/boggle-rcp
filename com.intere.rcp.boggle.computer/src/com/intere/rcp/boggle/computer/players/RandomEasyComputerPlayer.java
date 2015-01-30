package com.intere.rcp.boggle.computer.players;

import com.intere.rcp.boggle.computer.common.AbstractComputerClient;
import com.intere.rcp.boggle.core.managers.ServerDelegateService;
import com.intere.rcp.boggle.core.managers.SpellCheckService;

/**
 * Computer player that chooses random words for its strategy.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class RandomEasyComputerPlayer extends AbstractComputerClient {
    private boolean gameEnded;
    private GameRunnerThread slave;
    private long sleepTime = 15L;

    public RandomEasyComputerPlayer() {
        super();
    }

    @Override
    public void guessWord() {
        String word = SpellCheckService.getInstance().getRandomWord();

        while (getPlayer().getWordList().containsKey(word)) {
            word = SpellCheckService.getInstance().getRandomWord();
        }

        ServerDelegateService.getInstance().guessWord(getPlayer().getPlayerId(), getGame().getGameId(), word);

    }

    @Override
    public void startGame() {
        slave = new GameRunnerThread();
        slave.start();        
    }
    
    @Override
    public void endGame() {
        gameEnded = true;
    }
    
    public long getSleepTime() {
        return sleepTime;
    }

    /**
     * This Thread is responsible for performing the guessing of words.
     */
    private class GameRunnerThread extends Thread
    {
        @Override
        public void run() {
            while(!gameEnded) {
                
                guessWord();
                
                try {
                    sleep(getSleepTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }                
                
            }
        }
    }
}
