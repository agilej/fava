package me.donnior.fava;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FHashMap<K, V> extends HashMap<K, V> implements FMap<K, V> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2695835995810261605L;

    public FHashMap() {}

    public FHashMap(Map<K, V> map) {
        super(map);
    }

    @StateModified
    public void deleteIf(MPredicate<K, V> predict) {
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (predict.apply(entry.getKey(), entry.getValue())) {
                it.remove();
            }
        }
    }

    public void each(MConsumer<K, V> function) {
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            function.apply(entry.getKey(), entry.getValue());
        }
    }

    public FMap<K, V> merge(Map<K, V> map) {
        FHashMap<K, V> result = new FHashMap<K, V>(this);
        Iterator<Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public FMap<K, V> reject(MPredicate<K, V> predict) {
        FHashMap<K, V> result = new FHashMap<K, V>();
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            if (!predict.apply(key, value)) {
                result.put(key, value);
            }
        }
        return result;
    }

    public FMap<K, V> select(MPredicate<K, V> predict) {
        return this.findAll(predict);
    }

    public FMap<K, V> findAll(MPredicate<K, V> predict) {
        FHashMap<K, V> result = new FHashMap<K, V>();
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            if (predict.apply(key, value)) {
                result.put(key, value);
            }
        }
        return result;
    }
    
    @Override
    public K findKey(MPredicate<K, V> predicate) {
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            if (predicate.apply(key, value)) {
                return key;
            }
        }
        return null;
    }

    @Override
    public Map.Entry<K, V> findEntry(MPredicate<K, V> predicate) {
        Iterator<Entry<K, V>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            if (predicate.apply(key, value)) {
                return entry;
            }
        }
        return null;
    }    
}
