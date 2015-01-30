package com.intere.rcp.boggle.core.interfaces;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 * @param <ObserverType>
 *            The type of observer.
 */
public interface IObservable<ObserverType> {

    /**
     * Implementers must add an ObserverType to the list of listeners.
     * 
     * @param listener
     */
    public void addListener(ObserverType listener);

    /**
     * Implementers must remove an ObserverType from the list of listeners.
     * 
     * @param listener
     */
    public void removeListener(ObserverType listener);

}
