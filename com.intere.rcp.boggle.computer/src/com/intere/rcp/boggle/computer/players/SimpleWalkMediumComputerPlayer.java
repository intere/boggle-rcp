package com.intere.rcp.boggle.computer.players;

public class SimpleWalkMediumComputerPlayer extends SimpleWalkEasyComputerPlayer {
    @Override
    public long getSleepTime() {
        return 1000L;
    }
}
