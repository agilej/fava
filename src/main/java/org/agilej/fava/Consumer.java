package org.agilej.fava;

public interface Consumer<E> {

    /**
     * consume one object
     * @param e
     */
    void apply(E e);

}
