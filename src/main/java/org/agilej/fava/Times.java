package org.agilej.fava;

public class Times {
    
    private int times;
    
    private Times(int value) {
        if(value < 0){
            throw new IllegalArgumentException("negative value can't be a times");
        }
        this.times = value;
    }
    
    public static Times of(int value){
        return new Times(value);
    }
    
    public void each(Consumer<Integer> consumer){
        for(int i=0; i<this.times; i++){
            consumer.apply(i);
        }
    }
    
}
