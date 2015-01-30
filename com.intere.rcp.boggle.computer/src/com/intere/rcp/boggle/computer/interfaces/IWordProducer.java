package com.intere.rcp.boggle.computer.interfaces;

import com.intere.rcp.boggle.core.event.IModelListener;
import com.intere.rcp.boggle.core.interfaces.IObservable;
import com.intere.rcp.boggle.core.model.PairedList;

/**
 * This interface identifies you as a Word Producer.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public interface IWordProducer extends IObservable<IModelListener> {

    /**
     * This method is used by the implementor to produce words.
     */
    public void produceWords();

    /**
     * This method will provide the data structure of words that were most
     * recently produced for the consumer.
     * 
     * @return
     */
    public PairedList<String, String> getWords();
}
