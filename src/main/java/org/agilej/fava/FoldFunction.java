package org.agilej.fava;

public interface FoldFunction<U, V> {

    V apply(U element, V memo);

}
