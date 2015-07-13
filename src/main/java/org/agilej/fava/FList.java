package org.agilej.fava;

import java.util.Comparator;
import java.util.List;

import org.agilej.fava.util.StateModified;

public interface FList<E> extends List<E>, FCollection<E> {

    /**
     * get the element at the given index. The index can be negative, then will
     * get element at `size + index`
     *
     * <br />
     * <br />
     *
     * For example <tt>fList.at(-1)</tt> will return the last element
     * 
     * @param index index for one element, start from 0, can be negative
     * @return element at the index
     */
    E at(int index);

    /**
     * get the first element in the list. If list is empty the result will be
     * null.
     * 
     * @return the first element in the list or null if list is empty
     */
    E first();

    /**
     * get the last element in the list. If list is empty the result will be
     * null.
     * 
     * @return the last element in the list or null if list is empty
     */
    E last();

    /**
     * Returns the index of the first object for which the block returns true. 
     * Returns -1 if no match is found.
     *
     * @return the first index for element match the predicate, -1 if no one matched
     *
     */
    int indexOf(Predicate<E> function);

    E find(Predicate<E> function);

    void each(Consumer<E> function);

    boolean any(Predicate<E> function);

    FList<E> findAll(Predicate<E> predict);

    FList<E> select(Predicate<E> predict);

    <T> FList<T> map(Function<E, T> function);

    @StateModified
    FList<E> deleteIf(Predicate<E> predict);

    FList<E> reject(Predicate<E> predict);

    FList<E> compact();

    FList<E> top(int n);

    <T> FList<T> collect(Function<E, T> function);

    /**
     * add elements to end of the list with iteration
     * 
     * @param elements elements to be added to the caller list
     * @return the caller list itself
     */
    @StateModified
    FList<E> push(E... elements);
    
    /**
     * add elements to end of the list with iteration
     * 
     * @param elements elements to be added to the caller list
     * @return the caller list itself
     */
    @StateModified
    FList<E> push(List<E> elements);
    
    /**
     * Unlike {@link FList#push(List)}, this method will return a new list with all list's elements 
     * and the given plus elements.
     * 
     * <br />
     * 
     * @param elements
     * @return new list contains both the caller's elements and the params elements
     */
    FList<E> plus(E... elements);
    
    /**
     * Unlike {@link FList#push(List)}, this method will return a new list with all list's elements 
     * and the given plus elements.
     * 
     * @param elements
     * @return new list contains both the caller's elements and the params elements
     */
    FList<E> plus(List<E> elements);
    

    /**
     * Combines all elements of list by applying a binary operation, specified by a function.
     * For each element in list the function is passed an accumulator value (memo) and the element. 
     * The result of function's invocation becomes the new value for memo . 
     * At the end of the iteration, the final value of memo is the return value for the method.
     * 
     * @param init the initial memo for function
     * @param function
     * @return fold result
     */
    <T> T fold(T init, FoldFunction<E, T> function);
    
    /**
     * 
     * This method behaves like {@link #fold(Object, FoldFunction)}, except it use list's first element
     * as the initial memo.
     * 
     * <br /><br />
     * 
     * Unlike {@link #fold(Object, FoldFunction)}, this method's function must has the same type for memo
     * as the list's elements.
     * 
     * <br /><br />
     * 
     * Because this method will use the first element as the initial memo, so the list can't be empty,
     * otherwise an {@link RuntimeException} will be throw. 
     *
     * @param function
     * @return reduce result
     *
     */
    E reduce(FoldFunction<E, E> function);
    
    /**
     * 
     * sort current list and return it self. <br />
     * This method name was changed to sort2 because List in java8 has a built-in sort method
     * 
     * 
     * @param comparator
     * @return the caller itself
     */
    @StateModified
    FList<E> sort2(Comparator<? super E> comparator);
    
    /**
     * 
     * sort current list by element's some property, return it self.
     * @param function function can return a comparable value
     * @return the caller itself
     */
    @StateModified
    <T extends Comparable<T>> FList<E> sortBy(Function<E, T> function);
    
    /**
     * Calculates a sum from the elements.
     * 
     * @param function
     * @return sum
     */
    <T extends Number> T sum(Function<E, T> function);

}
