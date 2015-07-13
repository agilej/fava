package org.agilej.fava;

import java.util.Collection;

import org.agilej.fava.util.StateModified;

public interface FCollection<E> extends Collection<E> {

    
    
    /**
     * Return the first element which match the predicate
     * 
     * @param function
     * @return the first element which match the predicate
     */
    E find(Predicate<E> function);

    /**
     * 
     * Return a copy of all elements match the predicate
     * 
     * @param predicate
     * @return a copy of all elements match the predicate
     */
    FCollection<E> findAll(Predicate<E> predicate);

    /**
     * alias method for {@link #findAll(Predicate)}
     * 
     * @see #findAll(Predicate)
     */
    FCollection<E> select(Predicate<E> predicate);

    /**
     * Deletes every element of self for which predict evaluates to true. The
     * collection is changed instantly every time the block is called and not
     * after the iteration is over
     * <br />
     * <br />
     * This method is different from {@link #reject(Predicate)}, it will change 
     * the caller list self.
     * 
     * @param predicate the predicate to delete element
     * @return the call list itself with elements matched predicate removed
     */
    @StateModified
    FCollection<E> deleteIf(Predicate<E> predicate);

    /**
     * Returns a new collection containing the items in self for which the block
     * is not true.
     * <br />
     * <br />
     * This method is different from {@link #deleteIf(Predicate)}, it will not 
     * change the caller list, the result list is a new list.
     * 
     * @param predicate
     * @return a new collection containing the items in self for which the predicate is not true
     */
    FCollection<E> reject(Predicate<E> predicate);

    /**
     * Invokes function once for each element of self. Creates a new array
     * containing the values returned by the function
     * 
     * @param mapper mapper to convert one element to another type
     * @return
     */
    <T> FCollection<T> collect(Function<E, T> mapper);

    /**
     * alias method for {@link #collect(Function)}
     *
     */
    <T> FCollection<T> map(Function<E, T> mapper);

    /**
     * Calls function once for each element in self, passing that element as a
     * parameter.
     * 
     * @param action the action to perform on each element
     */
    void each(Consumer<E> action);
    
    /**
     * Same as {@link #each}, but passes the index of the element instead of the element itself. 
     */
    void eachIndex(Consumer<Integer> function);
    

    /**
     * Returns a copy of self with all null elements removed.
     * 
     * @return a copy of self with all null elements removed
     */
    FCollection<E> compact();

    /**
     * Passes each element of the collection to the function. The method returns
     * true if the function is matched.
     * 
     * @param predicate
     * @return return true if there is any every element match the given predicate
     */
    boolean any(Predicate<E> predicate);

    /**
     * Passes each element of the collection to the given function. The method
     * returns true if the block never returns false.
     * 
     * @param predicate
     * @return return true if all every element match the given predicate
     */
    boolean all(Predicate<E> predicate);

    /**
     * With a function is given, counts the number of elements yielding a true
     * value
     * 
     * @param predicate
     * @return counts the number of elements which match the given predicate
     */
    int count(Predicate<E> predicate);

    /**
     * Returns a copy of first n elements from collection. If n is bigger than
     * collection size, return a copy of all elements.
     * 
     * @return a copy of first n elements from collection
     */
    FCollection<E> top(int n);
    
//    public String join(String seprator);
    
    /**
     * Groups the collection by result of the function. 
     * Returns a Map where the keys are the evaluated result from the function 
     * and the values are arrays of elements in the collection that correspond to the key.
     * 
     * @param function
     * @return a grouped map
     */
    <T> FMap<T, FList<E>> groupBy(Function<E, T> function);

    /**
     *
     * Returns a string created by converting each element of the array to a string,
     * separated by the given separator.
     * If the separator is null, it will uses empty string.
     *
     * @param separator the separator used to concat element strings
     *
     * @return joined string
     */
    String join(String separator);

    /**
     *
     * Returns a string created by converting each element of the array to a string,
     * separated by empty string.
     *
     *
     * @see #join(String)
     */
    String join();
    
}
