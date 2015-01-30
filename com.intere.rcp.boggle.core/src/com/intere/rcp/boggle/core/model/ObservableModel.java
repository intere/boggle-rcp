package com.intere.rcp.boggle.core.model;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.event.ModelEvent;

/**
 * This class is the base implementation for an Observable model. You may
 * extend/override/adapt as necessary.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class ObservableModel extends Observable<IModelListener> {

    /**
     * Adds an {@link IModelListener} to the list of observers.
     * 
     * @see #addListener(IModelListener)
     * @param listener
     */
    public void addModelListener(IModelListener listener) {
        addListener(listener);
    }

    /**
     * Removes an {@link IModelListener} from the list of observers.
     * 
     * @see #removeListener(IModelListener)
     * @param listener
     */
    public void removeModelListener(IModelListener listener) {
        removeListener(listener);
    }

    /**
     * Informs all of the {@link IModelListener}s who are observing this Model
     * that a {@link ModelEvent} has occurred.
     * 
     * @param event
     */
    public void alertModelEvent(ModelEvent event) {
        for (IModelListener listener : getListeners()) {
            listener.onModelEvent(event);
        }
    }
}
