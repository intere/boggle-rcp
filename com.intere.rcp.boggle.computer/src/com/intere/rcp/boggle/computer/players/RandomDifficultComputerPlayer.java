package com.intere.rcp.boggle.computer.players;

public class RandomDifficultComputerPlayer extends RandomEasyComputerPlayer {
    @Override
    public long getSleepTime() {
        return 3L;
    }
}
