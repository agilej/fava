package me.donnior.fava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import me.donnior.fava.FList;
import static me.donnior.fava.util.FStatic.*;

import org.junit.Test;

public class FStaticTest {

    @Test
    public void testList(){
        FList<Integer> values = list(1,2);
        assertTrue(values.size() == 2);
    }
    
    @Test
    public void testMap(){
        assertTrue(map(1, "one").size() == 1);
        assertTrue(map(1, "one", 2, "two").size() == 2);
        assertTrue(map(1, "one", 2, "two", 3, "three").size() == 3);
        assertTrue(map(1, "one", 2, "two", 3, "three", 4, "four").size() == 4);
    }
    
        
}
