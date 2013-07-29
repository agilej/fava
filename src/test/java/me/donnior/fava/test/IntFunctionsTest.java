package me.donnior.fava.test;

import static org.junit.Assert.*;
import me.donnior.fava.FoldFunction;
import me.donnior.fava.functions.IntFunctions;
import me.donnior.fava.util.FLists;

import org.junit.Test;

public class IntFunctionsTest {

    @Test
    public void testBigerThan(){
        assertTrue(IntFunctions.biggerThan(1).apply(2));
        assertFalse(IntFunctions.biggerThan(2).apply(1));
    }
    
    @Test
    public void testEven(){
        assertTrue(IntFunctions.EVEN.apply(2));
        assertFalse(IntFunctions.EVEN.apply(1));
    }
    
    @Test
    public void testMod(){
        assertTrue(IntFunctions.mod(3, 1).apply(4));
        assertFalse(IntFunctions.mod(3, 0).apply(4));
        
        assertTrue(IntFunctions.mod(3).apply(6));
        assertFalse(IntFunctions.mod(5).apply(6));
        
        int result = FLists.range(1, 10).findAll(IntFunctions.mod(3)).fold(1, new FoldFunction<Integer, Integer>() {
            public Integer apply(Integer element, Integer init) {
                return element * init;
            }
        });
        
        System.out.println(result);
        
    }
    
}
