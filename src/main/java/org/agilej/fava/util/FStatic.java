package org.agilej.fava.util;

import org.agilej.fava.FHashMap;
import org.agilej.fava.FList;
import org.agilej.fava.FMap;

/**
 * Utilities class for `static import`, using it you can declare a list or map with a functional idiom.
 * 
 *     <pre>
 *     
 *     import static me.donnior.fava.util.FStatic.*;
 *     
 *     </pre>
 * Then use it as 
 *     <pre>
 *     
 *     FList&lt;Integer&gt; values = list(1,2,3,4,5);
 *     
 *     FMap&lt;Integer, String&gt; values = map(1,"one",2,"two");
 *     
 *     </pre>
 *
 */
public class FStatic {

    private FStatic(){}
    
    /**
     * Create a list
     * @param t
     * @return
     */
    public static <T> FList<T> list(T... t) {
        return FLists.create(t);
    }
    
    public static <K,V> FMap<K,V> map(K key, V value) {
        FMap<K, V> map = new FHashMap<K,V>();
        map.put(key, value);
        return map;
    }
    
    public static <K,V> FMap<K,V> map(K key1, V value1, K key2, V value2) {
        FMap<K, V> map = map(key1, value1);
        map.put(key2, value2);
        return map;
    }
    
    public static <K,V> FMap<K,V> map(K key1, V value1, K key2, V value2, K key3, V value3) {
        FMap<K, V> map = map(key1, value1, key2, value2);
        map.put(key3, value3);
        return map;
    }
    
    public static <K,V> FMap<K,V> map(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        FMap<K, V> map = map(key1, value1, key2, value2, key3, value3);
        map.put(key4, value4);
        return map;
    }
}
