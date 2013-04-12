package me.donnior.fava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import me.donnior.fava.FHashMap;
import me.donnior.fava.FMap;
import me.donnior.fava.MConsumer;
import me.donnior.fava.MPredict;

import org.junit.Test;

public class FHashMapTest {

	
	@Test
	public void testEach(){
		final FMap<Integer, String> c = prepareList();
		assertEquals("10", c.get(10));
		c.each(new MConsumer<Integer, String>(){
			public void apply(Integer key, String value) {
				if(key ==10){
					c.put(key, "ten");
				}
			}
		});
		assertEquals("1", c.get(1));
		assertEquals("1000", c.get(1000));
		assertEquals("ten", c.get(10));
	}

	
	@Test
	public void testDeleteIf(){
		FMap<Integer, String> c = prepareList();
		c.deleteIf(new MPredict<Integer, String> (){
			public boolean apply(Integer key, String value) {
				return key < 10;
			}
		});
		assertEquals(2, c.size());
		assertNull(c.get(1));
	}	
	
	
	@Test
	public void testReject(){
		FMap<Integer, String> c = prepareList();
		FMap<Integer, String> result = c.reject(new MPredict<Integer, String> (){
			public boolean apply(Integer key, String value) {
				return key > 10;
			}
		});
		assertEquals(3, c.size());
		assertEquals(2, result.size());
		assertNull(result.get(1000));
	}
	
    @Test
    public void testSelect(){
        FMap<Integer, String> c = prepareList();
        FMap<Integer, String> result = c.select(new MPredict<Integer, String> (){
            public boolean apply(Integer key, String value) {
                return key > 10;
            }
        });
        assertEquals(1, result.size());
        assertNotNull(result.get(1000));
    }	

    @Test
    public void testMerge(){
        FMap<Integer, String> c = prepareList();
        
        Map<Integer, String> mapToMerge = new HashMap<Integer, String>();
        mapToMerge.put(1, "one");
        mapToMerge.put(10000, "10000");
        
        FMap<Integer, String> result = c.merge(mapToMerge);
        assertEquals(4, result.size());
        assertEquals("one", result.get(1));
    }   
    

	private FMap<Integer, String> prepareList() {
		FMap<Integer, String> map = new FHashMap<Integer, String>();
		map.put(1, "1");
		map.put(10, "10");
		map.put(1000, "1000");
		return map;
//		return FLists.create(new A(8), new A(12), new A(234));
	}

}
