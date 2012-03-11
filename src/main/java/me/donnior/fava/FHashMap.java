package me.donnior.fava;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.Map.Entry;

public class FHashMap<K, V> extends HashMap<K, V> implements FMap<K, V>{
	
	public FHashMap(){ }
	
	public FHashMap(Map<K,V> map){
		super(map);
	}

	@Override
	public void deleteIf(MPredict<K, V> predict) {
		Iterator<Entry<K,V>> it = this.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,V> entry = it.next();
			if(predict.apply(entry.getKey(), entry.getValue())){
				it.remove();
			}
		}	
	}

	@Override
	public void each(MFunction<K, V> function) {
		Iterator<Entry<K,V>> it = this.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,V> entry = it.next();
			function.apply(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void merge(Map<K, V> map) {
		Iterator<Entry<K,V>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,V> entry = it.next();
			this.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public FMap<K, V> reject(MPredict<K, V> predict) {
		FHashMap<K,V> result = new FHashMap<K, V>();
		Iterator<Entry<K,V>> it = this.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			if(!predict.apply(key, value)){
				result.put(key, value);
			}
		}
		return result;
	}

	@Override
	public FMap<K, V> select(MPredict<K, V> predict) {
		FHashMap<K,V> result = new FHashMap<K, V>();
		Iterator<Entry<K,V>> it = this.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			if(predict.apply(key, value)){
				result.put(key, value);
			}
		}
		return result;
	}

}
