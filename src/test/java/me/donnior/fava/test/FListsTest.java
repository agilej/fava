package me.donnior.fava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import me.donnior.fava.util.FLists;

import org.junit.Test;

public class FListsTest {

    @Test
    public void testNewEmpty(){
        List<?> list = FLists.newEmptyList();
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testVarParams(){
        List<String> list = FLists.create("one");
        assertEquals("one",list.get(0));
        assertEquals(1, list.size());
    }
    
    @Test
    public void testCreatedWithList(){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        
        List<String> result = FLists.create(list);
        assertEquals(2, result.size());
        assertEquals("two", result.get(1));
    }
    
}
