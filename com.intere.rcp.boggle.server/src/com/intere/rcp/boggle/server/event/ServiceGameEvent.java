package com.intere.rcp.boggle.server.event;

/**
 * This class is the model that describes the Service Game event that occurred.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ServiceGameEvent {
    private String gameUid;
    private ServiceGameEventType eventType;
    
    public enum ServiceGameEventType
    {
        PlayerJoined,
        PlayerLeft,
        GameStarted,
        GameEnded,
        StatsUpdated,
        WordGuessed;
    }
    
    public String getGameUid() {
        return gameUid;
    }
    
    public ServiceGameEventType getEventType() {
        return eventType;
    }
}
