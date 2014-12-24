package org.agilej.fava;

public interface MConsumer<K, V> {

    void apply(K key, V value);

}
