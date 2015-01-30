package com.intere.rcp.boggle.core.event;

/**
 * This interface is to be implemented by those who wish to be notified of
 * {@link ModelEvent}s.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IModelListener {

    /**
     * This method notifies the observer that a {@link ModelEvent} has occurred.
     * 
     * @param event
     */
    public void onModelEvent(ModelEvent event);
}
