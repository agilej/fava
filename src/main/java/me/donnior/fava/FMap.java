package me.donnior.fava;

import java.util.Map;

import me.donnior.fava.util.StateModified;

public interface FMap<K, V> extends Map<K, V> {

    /**
     * Deletes every element of self for which predict evaluates to true. The
     * map is changed instantly every time the block is called and not after the
     * iteration is over
     * 
     * @param predict
     * @return
     */
    @StateModified
    void deleteIf(MPredicate<K, V> predicate);

    /**
     * Calls function once for each entry in self, passing that element as a
     * parameter.
     * 
     * @param function
     */
    void each(MConsumer<K, V> function);

    /**
     * Returns a new hash containing the contents of other_map and the contents
     * of map. The value for entries with duplicate keys will be that of
     * other_hash.
     * 
     * @param map
     */
    FMap<K, V> merge(Map<K, V> map);

    /**
     * Returns a new map containing the items in self for which the block is not
     * true
     * 
     * @param predict
     * @return
     */
    FMap<K, V> reject(MPredicate<K, V> predicate);

    /**
     * 
     * Return a new map with copy of all elements match the predict
     * 
     * @param predict
     * @return
     */
    FMap<K, V> findAll(MPredicate<K, V> predicate);

    /**
     * alias method for {@link #findAll(Predict)}
     * 
     * @param predict
     * @return
     */
    FMap<K, V> select(MPredicate<K, V> predicate);
    
    /**
     * find the first entry key which match the predicate
     * @param predictate
     * @return
     */
    K findKey(MPredicate<K,V> predicate);
    
    /**
     * 
     * find the first entry key which match the predicate
     * @param predicate
     * @return
     */
    Map.Entry<K, V> findEntry(MPredicate<K, V> predicate);

}
