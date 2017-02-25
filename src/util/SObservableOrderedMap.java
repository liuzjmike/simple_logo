package util;

import java.util.AbstractMap.SimpleEntry;

public class SObservableOrderedMap<K, V> extends SObservableList<SimpleEntry<K, V>> {
    
    public V get(K key) throws Exception {
        for(SimpleEntry<K, V> entry: this) {
            if(entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new Exception();
    }
    
    public void put(K key, V value) {
        for(SimpleEntry<K, V> entry: this) {
            if(entry.getKey().equals(key)) {
                entry.setValue(value);
            }
        }
        add(new SimpleEntry<>(key, value));
    }

}
