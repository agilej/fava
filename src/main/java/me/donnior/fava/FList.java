package me.donnior.fava;

import java.util.List;


public interface FList<E> extends List<E>, FCollection<E>{
	
    /**
     * get the element at the given index. 
     * The index can be negative, then will get element at `size + index` 
     * @param index
     * @return
     */
	E at(int index);
	
	/**
	 * get the first element in the list. If list is empty the result will be null.
	 * @return the first element in the list or null if list is empty
	 */
	E first();
	
	/**
     * get the last element in the list. If list is empty the result will be null.
     * @return the last element in the list or null if list is empty
     */
	E last();
	
	E find(Predict<E> function);

	void each(Consumer<E> function);
	
	boolean any(Predict<E> function);
	
	FList<E> findAll(Predict<E> predict);
	
	FList<E> select(Predict<E> predict);
	
	<T> FList<T> map(Function<E, T> function);
	
	FList<E> deleteIf(Predict<E> predict);
	
	FList<E> reject(Predict<E> predict);

	FList<E> compact();
	
	FList<E> top(int n);
	
	<T> FList<T> collect(Function<E, T> function);

	/**
	 * add elements to end of the list with iteration
	 * @param elements
	 * @return 
	 */
	FList<E> push(E...elements);
	
	<T> T fold(FoldFunction<E, T> function, T init);
	
}
