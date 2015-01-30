package com.intere.rcp.boggle.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a base implementation for an Observable object.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 * @param <ObserverType>
 *            The type of Observer.
 */
public class Observable<ObserverType> {
    private List<ObserverType> listeners = new ArrayList<ObserverType>();

    /**
     * Adds a Type to the list of observers.
     * 
     * @param listener
     */
    public void addListener(ObserverType listener) {
        listeners.add(listener);
    }

    /**
     * Removes a Type from the list of observers.
     * 
     * @param listener
     */
    public void removeListener(ObserverType listener) {
        listeners.remove(listener);
    }

    /**
     * Get the list of listeners.
     * 
     * @return
     */
    protected List<ObserverType> getListeners() {
        return listeners;
    }
}
