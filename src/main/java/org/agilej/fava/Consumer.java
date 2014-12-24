package org.agilej.fava;

public interface Consumer<E> {

    void apply(E e);

}
