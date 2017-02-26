package util;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class SObservableOrderedMap<K, V> extends SObservableList<Entry<K, V>> {
    
    public V get(K key) throws Exception {
        for(Entry<K, V> entry: this) {
            if(entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new Exception();
    }
    
    public void put(K key, V value) {
        for(Entry<K, V> entry: this) {
            if(entry.getKey().equals(key)) {
                entry.setValue(value);
                notifyObservers();
                return;
            }
        }
        add(new SimpleEntry<>(key, value));
    }

}
