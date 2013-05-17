package me.donnior.fava;

public interface MPredicate<K, V> {

    boolean apply(K key, V value);

}
