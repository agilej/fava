package org.agilej.fava;

public interface Predicate<E> {
    /**
     *
     * test the given object match one predicate
     *
     * @param e
     * @return
     */
    boolean apply(E e);
}
