package org.agilej.fava;

public interface Function<E, T> {

    /**
     * calculate and mapping one object to another one
     *
     * @param e
     * @return
     */
    T apply(E e);

}
