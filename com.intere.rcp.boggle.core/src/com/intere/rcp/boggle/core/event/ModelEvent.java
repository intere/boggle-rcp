package com.intere.rcp.boggle.core.event;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * This class is the Model Event.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
@XmlRootElement(name="model-event")
public class ModelEvent {
    
    /** The source of the event.  */
    @XmlTransient
    private Object source;

    /** The model that was modified.  */
    @XmlTransient
    private Object model;

    /** The data that was added/removed.  */
    private Object data;
    
    /** The event type.  */
    private ModelEventType eventType;

    /**
     * Enumeration of model event types.
     * 
     */
    @XmlType(name="ModelEventType")
    public enum ModelEventType {
        ModelUpdated, DataAdded, DataRemoved;
    }
    
    /**
     * Enumeration of various types of models that were updated.
     */
    @XmlType(name="ModelType")
    public enum ModelType {
        PlayerList, GameList, 
    }

    /**
     * Constructor that sets the Event Type.
     * 
     * @param eventType
     */
    private ModelEvent(ModelEventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Factory creation method that creates a {@link ModelEventType#DataRemoved}
     * event type.
     * 
     * @param source
     * @param model
     * @param data
     * @return
     */
    public static ModelEvent createDataRemovedEvent(Object source, Object model, Object data) {
        ModelEvent event = new ModelEvent(ModelEventType.DataRemoved);
        event.source = source;
        event.model = model;
        event.data = data;

        return event;
    }

    /**
     * Factory creation method that creates a {@link ModelEventType#DataAdded}
     * event type.
     * 
     * @param source
     * @param model
     * @param data
     * @return
     */
    public static ModelEvent createDataAddedEvent(Object source, Object model, Object data) {
        ModelEvent event = new ModelEvent(ModelEventType.DataAdded);
        event.source = source;
        event.model = model;
        event.data = data;

        return event;
    }

    /**
     * Factory creation method that creates a
     * {@link ModelEventType#ModelUpdated} event type.
     * 
     * @param source
     * @param model
     * @param data
     * @return
     */
    public static ModelEvent createModelUpdatedEvent(Object source, Object model) {
        ModelEvent event = new ModelEvent(ModelEventType.ModelUpdated);
        event.source = source;
        event.model = model;

        return event;
    }

    public Object getSource() {
        return source;
    }

    public Object getModel() {
        return model;
    }

    public Object getData() {
        return data;
    }

    public ModelEventType getEventType() {
        return eventType;
    }
}
