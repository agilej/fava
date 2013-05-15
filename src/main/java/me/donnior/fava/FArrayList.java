package me.donnior.fava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FArrayList<E> extends ArrayList<E> implements FList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FArrayList(){}
	
	public FArrayList(List<E> list){
		super(list);
	}
	
	
	public E find(Predicate<E> function) {
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(function.apply(e)){
				return e;
			}
		}
		return null;
	}
	
	public FList<E> findAll(Predicate<E> function){
		FArrayList<E> result = new FArrayList<E>();
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(function.apply(e)){
				result.add(e);
			}
		}
		return result;
	}

	
	public <T> FList<T> collect(Function<E, T> function) {
		FArrayList<T> result = new FArrayList<T>();
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			result.add(function.apply(it.next()));
		}
		return result;
	}
	
	public <T> FList<T> map(Function<E, T> function) {
	    return this.collect(function);
	}

	
	public void each(Consumer<E> function) {
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			function.apply(it.next());
		}
	}

	
	public E at(int index) {
		if(index >= 0){
			return this.get(index);
		} else {
			return this.get(this.size() + index);
		}
	}

	
	public FList<E> compact() {
		FArrayList<E> result = new FArrayList<E>();
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(e != null){
				result.add(e);
			}
		}
		return result;
	}

	
	public E first() {
		if(this.isEmpty()) {
			return null;
		}
		return this.get(0);
	}

	
	public E last() {
		if(this.isEmpty()) {
			return null;
		}
		return this.get(this.size() - 1);
	}

	
	public FList<E> select(Predicate<E> predict) {
		return this.findAll(predict);
	}

	
	public FList<E> deleteIf(Predicate<E> predict) {
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(predict.apply(e)){
				it.remove();
			}
		}
		return this;
	}
	
	
	public FList<E> reject(Predicate<E> predict) {
		FArrayList<E> result = new FArrayList<E>();
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(!predict.apply(e)){
				result.add(e);
			}
		}
		return result;
	}
	
	
	public boolean any(Predicate<E> function) {
		return this.find(function) != null;
	}
		
	
	public boolean all(Predicate<E> function){
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(!function.apply(e)){
				return false;
			}
		}
		return true;		
	}

	
	public int count(Predicate<E> function) {
		int count = 0;
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(function.apply(e)){
				count += 1;
			}
		}
		return count;	
	}
	
	public FList<E> top(int n){
		FArrayList<E> result = new FArrayList<E>();
		int count = 0;
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			E e = it.next();
			if(count < n){
				result.add(e);
				count += 1;
			}else{
				break;
			}
		}
		return result;
	}
	
	public FList<E> push(E...elements){
		if(elements != null){
			for(E e : elements){
				this.add(e);
			}
		}
		return this;
	}	

	public <T> T fold(T init, FoldFunction<E, T> function){
		Iterator<E> it = this.iterator();
		T result = init;
		while(it.hasNext()){
			E e = it.next();
			result = function.apply(e, result);
		}
		return result;
	}
	
}
