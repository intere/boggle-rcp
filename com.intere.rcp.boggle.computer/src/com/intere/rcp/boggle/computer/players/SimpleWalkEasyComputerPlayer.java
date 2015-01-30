package com.intere.rcp.boggle.computer.players;

import com.intere.rcp.boggle.computer.common.AbstractComputerClient;
import com.intere.rcp.boggle.computer.producers.SimpleWalkProducer;
import com.intere.rcp.boggle.core.managers.ServerDelegateService;

/**
 * This class is the Simple Walk, Easy Computer Player. It utilizes the
 * {@link SimpleWalkProducer} to populate words.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class SimpleWalkEasyComputerPlayer extends AbstractComputerClient {

    private boolean gameEnded;

    private long sleepTime = 2500;

    private GameRunnerThread slave;

    private SimpleWalkProducer producer;

    @Override
    public void endGame() {
        gameEnded = true;
    }

    @Override
    public void guessWord() {

        if (producer.getWords().getList().size() == 0) {
            producer.produceWords();
        }

        if (producer.getWords().getList().size() > 0) {
            String word = producer.getWords().getList().get(0);
            producer.getWords().remove(word);
            ServerDelegateService.getInstance().guessWord(getPlayer().getPlayerId(), getGame().getGameId(), word);
        }
    }

    @Override
    public void startGame() {
        producer = new SimpleWalkProducer(getGame().getBoard());
        slave = new GameRunnerThread();
        slave.start();
    }

    public long getSleepTime() {
        return sleepTime;
    }

    /**
     * This class is responsible for throttling the word guessing.
     */
    private class GameRunnerThread extends Thread {
        @Override
        public void run() {
            try {
                sleep(5000L);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            while (!gameEnded) {
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
