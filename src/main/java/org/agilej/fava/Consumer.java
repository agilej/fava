package org.agilej.fava;

public interface Consumer<E> {

    /**
     * consume one object
     * @param object the object this consumer will perform on
     */
    void apply(E object);

}
