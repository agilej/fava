package me.donnior.fava;

import java.util.Collection;

public interface FCollection<E> extends Collection<E> {

    /**
     * Returns the index of the first object for which the block returns true. 
     * Returns -1 if no match is found.
     */
    int indexOf(Predicate<E> function);
    
    /**
     * Return the first element which match the predict
     * 
     * @param function
     * @return
     */
    E find(Predicate<E> function);

    /**
     * 
     * Return a copy of all elements match the predict
     * 
     * @param predict
     * @return
     */
    FCollection<E> findAll(Predicate<E> predict);

    /**
     * alias method for {@link #findAll(Predicate)}
     * 
     * @param predict
     * @return
     */
    FCollection<E> select(Predicate<E> predict);

    /**
     * Deletes every element of self for which predict evaluates to true. The
     * collection is changed instantly every time the block is called and not
     * after the iteration is over
     * 
     * This method is different from {@link #reject(Predicate)}, it will change 
     * the caller list self.
     * 
     * @param predict
     * @return
     */
    FCollection<E> deleteIf(Predicate<E> predict);

    /**
     * Returns a new collection containing the items in self for which the block
     * is not true.
     * 
     * This method is differenct from {@link #deleteIf(Predicate)}, it will not 
     * change the caller list, the result list is a new list.
     * 
     * @param predict
     * @return
     */
    FCollection<E> reject(Predicate<E> predict);

    /**
     * Invokes function once for each element of self. Creates a new array
     * containing the values returned by the function
     * 
     * @param function
     * @return
     */
    <T> FCollection<T> collect(Function<E, T> function);

    /**
     * alias method for {@link #collect(Function)}
     * 
     * @param function
     * @return
     */
    <T> FCollection<T> map(Function<E, T> function);

    /**
     * Calls function once for each element in self, passing that element as a
     * parameter.
     * 
     * @param function
     */
    void each(Consumer<E> function);
    
    /**
     * Same as {@link #each}, but passes the index of the element instead of the element itself. 
     */
    void eachIndex(Consumer<Integer> function);
    

    /**
     * Returns a copy of self with all null elements removed.
     * 
     * @return
     */
    FCollection<E> compact();

    /**
     * Passes each element of the collection to the function. The method returns
     * true if the function is matched.
     * 
     * @param function
     * @return
     */
    boolean any(Predicate<E> function);

    /**
     * Passes each element of the collection to the given function. The method
     * returns true if the block never returns false.
     * 
     * @param function
     * @return
     */
    boolean all(Predicate<E> function);

    /**
     * With a function is given, counts the number of elements yielding a true
     * value
     * 
     * @param function
     * @return
     */
    int count(Predicate<E> function);

    /**
     * Returns a copy of first n elements from collection. If n is bigger than
     * collection size, return a copy of all elements.
     * 
     * @return
     */
    FCollection<E> top(int n);
}
