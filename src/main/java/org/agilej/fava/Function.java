package org.agilej.fava;

public interface Function<E, T> {

    /**
     * calculate and mapping one object to another one
     *
     * @param object the object this function will perform on
     * @return the mapped object
     */
    T apply(E object);

}
