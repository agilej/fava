package me.donnior.fava;

import java.util.Map;

public interface FMap<K, V> extends Map<K,V>{
	
	void deleteIf(MPredict<K,V> predict);
	
	void each(MFunction<K, V> function);

	void merge(Map<K, V> map);
	
	FMap<K, V> reject(MPredict<K,V> predict);
	
	FMap<K, V> select(MPredict<K, V> predict);
	
}
