package com.intere.rcp.boggle.server.event;

/**
 * This class is the model that describes the Service Event that occurred.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ServiceEvent {
    private ServiceEventType eventType;    
    
    public enum ServiceEventType
    {
        GameCreated,
        GameDestroyed,
        GameListUpdated;
    }
    
    
    public ServiceEventType getEventType() {
        return eventType;
    }
}
