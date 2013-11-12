package me.donnior.fava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import me.donnior.fava.FList;
import me.donnior.fava.util.FLists;

import org.junit.Test;

public class FListsTest {

    @Test(expected=NullPointerException.class)
    public void testCreateWithNull(){
        FLists.create((List<Object>)null);
    }
    
    @Test
    public void testSafeCreat(){
        List<?> list = FLists.safeCreate((List<Object>)null);
        assertTrue(list.isEmpty());
        
        List<String> names = null;
        assertTrue(FLists.safeCreate(names).isEmpty());
    }
    
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
    
    @Test
    public void testCreateWithRange(){
        FList<Integer> range = FLists.range(0, 1000);
        
        assertEquals(1001, range.size());
        
        assertTrue(0 == range.first());
        assertTrue(1000 == range.last());
        
        FList<String> sr = FLists.range('a', 'z');
        assertEquals(26, sr.size());
        assertEquals("b", sr.at(1));
    }
    
}
