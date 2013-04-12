package me.donnior.fava;

public interface Consumer<E> {
	
	void apply(E e);

}
