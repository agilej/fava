package me.donnior.fava.util;

import java.util.List;

import me.donnior.fava.FArrayList;
import me.donnior.fava.FList;

public final class FLists {

    private FLists() {
    }

    public static <T> FList<T> create(List<T> array) {
        return new FArrayList<T>(array);
    }

    public static <T> FList<T> create(T... t) {
        FList<T> list = new FArrayList<T>();
        for (T temp : t) {
            list.add(temp);
        }
        return list;
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

}
