package me.donnior.fava;

public interface MConsumer<K, V> {

	void apply(K key, V value);
	
}
