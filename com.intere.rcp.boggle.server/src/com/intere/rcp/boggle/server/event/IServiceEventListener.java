package com.intere.rcp.boggle.server.event;

/**
 * This interface is to be implemented by classes that wish to observe a
 * {@link ServiceEvent}.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IServiceEventListener {

    /**
     * The callback method that notifies the observer of a {@link ServiceEvent}.
     * 
     * @param event
     */
    public void onServiceEvent(ServiceEvent event);
}
