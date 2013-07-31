package me.donnior.fava.util;

import java.util.Collection;

import me.donnior.fava.FArrayList;
import me.donnior.fava.FList;

public final class FLists {

    private FLists() {
    }

    public static <T> FList<T> create(Collection<T> array) {
        return new FArrayList<T>(array);
    }

    public static <T> FList<T> create(T... t) {
        FList<T> list = new FArrayList<T>();
        for (T temp : t) {
            list.add(temp);
        }
        return list;
    }
    
    public static <T> FList<T> $(T... t) {
        return create(t);
    }
    
    public static <T> FList<T> $(Collection<T> array){
        return create(array);
    }
    
    public static <T> FList<T> newEmptyList() {
        return new FArrayList<T>();
    }
    
    public static FList<Integer> range(int start, int end){
        if(start > end){
            throw new IllegalArgumentException("a range's end must greater than start");
        }
        FArrayList<Integer> array = new FArrayList<Integer>();
        for(int i=start; i<=end; i++){
            array.add(i);
        }
        return array;
    }

    public static FList<String> range(char start, char end){
        if(start > end){
            throw new IllegalArgumentException("a range's end must greater than start");
        }
        FArrayList<String> array = new FArrayList<String>();
        for(char i=start; i<=end; i++){
            array.add(String.valueOf(i));
        }
        return array;
    }
    
}
