package me.donnior.fava.functions;

import me.donnior.fava.Predicate;

public final class IntFunctions {
    
    private IntFunctions(){}

	public static Predicate<Integer> biggerThan(final int i){
		return new  Predicate<Integer>(){
			public boolean apply(Integer e) {
				return e > i;
			}
			
		};
	}
	
	
	public static final Predicate<Integer> EVEN =	new  Predicate<Integer>(){
		public boolean apply(Integer e) {
			return e % 2 == 0;
		}
	};
	
	
}
