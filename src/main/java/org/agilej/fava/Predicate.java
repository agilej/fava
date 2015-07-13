package org.agilej.fava;

public interface Predicate<E> {
    /**
     *
     * test the given object match one predicate
     *
     * @param object
     * @return true if this predicate match the given object
     */
    boolean apply(E object);
}
