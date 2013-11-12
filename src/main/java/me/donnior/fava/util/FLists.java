package me.donnior.fava.util;

import java.util.Collection;

import me.donnior.fava.FArrayList;
import me.donnior.fava.FList;

/**
 *  Factory utility class for create list. For most time, {@link FStatic} is the first recommendation.
 *
 */
public final class FLists {

    private FLists() {}

    public static <T> FList<T> create(Collection<T> array) {
        return new FArrayList<T>(array);
    }

    /**
     * Create a list safely which will create a empty list if parameter is null
     */
    public static <T> FList<T> safeCreate(Collection<T> array) {
        if(array == null){
           return newEmptyList(); 
        }
        return create(array);
    }
    
    
    public static <T> FList<T> create(T... t) {
        FList<T> list = new FArrayList<T>();
        for (T temp : t) {
            list.add(temp);
        }
        return list;
    }
    
    /**
     * Create a list safely which will create a empty list if parameter is null
     */
//    public static <T> FList<T> safeCreate(T... t) {
//        if(t == null){
//            return newEmptyList();
//        }
//        return create(t);
//    }
//    
    
    public static <T> FList<T> $(T... t) {
        return create(t);
    }
    
    public static <T> FList<T> $(Collection<T> collection){
        return safeCreate(collection);
    }
    
    public static <T> FList<T> newEmptyList() {
        return new FArrayList<T>();
    }
    
    /**
     * 
     * create a numeric array from start to end, note both the start and end will be included in array. 
     * 
     * @param start start number
     * @param end end number
     * @return
     */
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

    /**
     * create a char array from start to end, note both the start and end will be included in array.
     * @param start
     * @param end
     * @return
     */
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
