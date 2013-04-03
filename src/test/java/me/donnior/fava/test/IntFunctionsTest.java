package me.donnior.fava.test;

import static org.junit.Assert.*;
import me.donnior.fava.functions.IntFunctions;

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
    
}
