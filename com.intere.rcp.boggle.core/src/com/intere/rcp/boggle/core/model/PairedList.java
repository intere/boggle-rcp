package com.intere.rcp.boggle.core.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.intere.rcp.boggle.core.event.ModelEvent;
import com.intere.rcp.boggle.core.model.jaxb.MapAdapter;

/**
 * This class is a data structure that maintains a Map and a List in parallel
 * for you.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class PairedList<KeyType, ObjectType> extends ObservableModel {

    /** The map.  */
    @XmlElement(name="map")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<KeyType, ObjectType> objectMap = new Hashtable<KeyType, ObjectType>();

    /** The list.  */
    @XmlTransient
    private List<ObjectType> objectList = new ArrayList<ObjectType>();

    /**
     * Adds an Object to the list.
     * 
     * @param key
     * @param obj
     */
    public void add(KeyType key, ObjectType obj) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, obj);
            objectList.add(obj);
            alertModelEvent(ModelEvent.createDataAddedEvent(this, objectList, obj));
        } else {
            objectList.set(objectList.indexOf(objectMap.get(key)), obj);
            objectMap.put(key, obj);
            alertModelEvent(ModelEvent.createModelUpdatedEvent(this, objectList));
        }
    }

    /**
     * Removes an Object from the list.
     * 
     * @param key
     */
    public ObjectType remove(KeyType key) {

        ObjectType data = null;
        if (objectMap.containsKey(key)) {
            objectList.remove(objectMap.get(key));
            data = objectMap.remove(key);

            alertModelEvent(ModelEvent.createDataRemovedEvent(this, objectList, data));
        }

        return data;
    }

    /**
     * Clears the lists out.
     */
    public void clear() {
        objectMap.clear();
        objectList.clear();

        alertModelEvent(ModelEvent.createModelUpdatedEvent(this, objectList));
    }

    /**
     * Get an object from the list via its key.
     * 
     * @param key
     * @return
     */
    public ObjectType get(KeyType key) {
        return objectMap.get(key);
    }

    /**
     * This method gets you the list of data.
     * 
     * @return
     */
    public List<ObjectType> getList() {
        return objectList;
    }

    /**
     * This method gives you the keys as a list.
     * 
     * @return
     */
    public List<KeyType> getKeyList() {
        List<KeyType> list = new ArrayList<KeyType>();
        list.addAll(objectMap.keySet());

        return list;
    }

    /**
     * This method will tell you if the Paired List has the provided key yet.
     * @param key
     * @return
     */
    public boolean containsKey(KeyType key) {
        return objectMap.containsKey(key);
    }
}
