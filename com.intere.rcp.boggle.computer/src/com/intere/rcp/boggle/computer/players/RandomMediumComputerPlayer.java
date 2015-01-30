package com.intere.rcp.boggle.computer.players;

/**
 * Medium computer player; uses random strategy.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class RandomMediumComputerPlayer extends RandomEasyComputerPlayer {

    @Override
    public long getSleepTime() {
        return 7L;
    }
}
