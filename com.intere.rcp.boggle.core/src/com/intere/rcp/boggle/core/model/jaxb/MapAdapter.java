package com.intere.rcp.boggle.core.model.jaxb;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class MapAdapter<K, V> extends XmlAdapter<MapAdapter.Adapter<K, V>, Map<K, V>> {

    @XmlType(name="map")
    @XmlAccessorType(XmlAccessType.NONE)
    public final static class Adapter<K, V> {

        @XmlElement(name="list")
        protected List<MyEntry<K, V>> key = new LinkedList<MyEntry<K, V>>();

        public Adapter() {
        }

        public Adapter(Map<K, V> original) {
            for (Map.Entry<K, V> entry : original.entrySet()) {
                key.add(new MyEntry<K, V>(entry));
            }
        }
    }

    @XmlType(name="entry")
    @XmlAccessorType(XmlAccessType.NONE)
    public final static class MyEntry<K, V> {

        @XmlElement(name="key")
        protected K key;

        @XmlElement(name="value")
        protected V value;

        /**
         * Default Constructor.
         */
        public MyEntry() {
        }

        public MyEntry(Map.Entry<K, V> original) {
            key = original.getKey();
            value = original.getValue();
        }
    }

    @Override
    public Adapter<K, V> marshal(Map<K, V> obj) {
        return new Adapter<K, V>(obj);
    }

    @Override
    public Map<K, V> unmarshal(Adapter<K, V> obj) {
        throw new UnsupportedOperationException("unmarshalling is never performed");
    }

}
