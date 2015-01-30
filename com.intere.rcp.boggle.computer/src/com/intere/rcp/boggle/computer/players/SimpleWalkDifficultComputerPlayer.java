package com.intere.rcp.boggle.computer.players;

public class SimpleWalkDifficultComputerPlayer extends SimpleWalkEasyComputerPlayer {
    @Override
    public long getSleepTime() {
        return 500L;
    }
}
