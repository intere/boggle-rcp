package com.intere.rcp.boggle.server.event;

/**
 * This interface is to be implemented by classes that wish to observe a
 * {@link ServiceGameEvent}.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IServiceGameEventListener {
    
    /**
     * The callback method that notifies the observer of a {@link ServiceGameEvent}.
     * 
     * @param event
     */
    public void onServiceGameEvent(ServiceGameEvent event);
}
