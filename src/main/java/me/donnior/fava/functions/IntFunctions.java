package me.donnior.fava.functions;

import me.donnior.fava.Predict;

public final class IntFunctions {
    
    private IntFunctions(){}

	public static Predict<Integer> biggerThan(final int i){
		return new  Predict<Integer>(){
			public boolean apply(Integer e) {
				return e > i;
			}
			
		};
	}
	
	
	public static final Predict<Integer> EVEN =	new  Predict<Integer>(){
		public boolean apply(Integer e) {
			return e % 2 == 0;
		}
	};
	
	
}
