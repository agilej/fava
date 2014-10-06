package me.donnior.fava.test;

import static me.donnior.fava.util.FLists.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import me.donnior.fava.Consumer;
import me.donnior.fava.FArrayList;
import me.donnior.fava.FCollection;
import me.donnior.fava.FHashMap;
import me.donnior.fava.FList;
import me.donnior.fava.FMap;
import me.donnior.fava.FoldFunction;
import me.donnior.fava.Function;
import me.donnior.fava.Predicate;
import me.donnior.fava.util.FLists;

import org.junit.Test;

public class FArrayListTest {

    @Test
    public void testIndexOf() {
        FList<A> c = prepareList();
        int index = c.indexOf(new Predicate<A>(){    
            public boolean apply(A a){
                return a.i > 10;
            }
        });
        assertEquals(1, index);
        
        index = c.indexOf(new Predicate<A>(){    
            public boolean apply(A a){
                return a.i > 0;
            }
        });
        assertEquals(0, index);
        
        index = c.indexOf(new Predicate<A>(){    
            public boolean apply(A a){
                return a.i > 1000;
            }
        });
        assertEquals(-1, index);
    }
    
	@Test
	public void testFindOne() {
		FCollection<A> c = prepareList();
		A a = c.find(new Predicate<A>(){	
			public boolean apply(A a){
				return a.i > 10;
			}
		});
		assertEquals(12, a.i);
	}
	
	@Test
	public void testFindAll(){
		FList<A> c = prepareList();
		FList<A> as = c.findAll(new Predicate<A>(){	
			public boolean apply(A a){
				return a.i > 10;
			}
		});
		assertEquals(2, as.size());
	}
	
    @Test
    public void testSelect(){
        FList<A> c = prepareList();
        FList<A> as = c.select(new Predicate<A>(){   
            public boolean apply(A a){
                return a.i > 10;
            }
        });
        assertEquals(2, as.size());
    }	
	
	@Test
	public void testMap(){
	    FList<A> c = prepareList();
	    FList<B> as = c.map(new Function<A,B>(){	
			public B apply(A a){
				return new B(a.i * 2);
			}
		});
		
		assertEquals(3, as.size());
		
		Iterator<B> it = as.iterator();
		assertEquals(16, it.next().i);
		assertEquals(24, it.next().i);
		assertEquals(B.class, it.next().getClass());
	}
	
	
	@Test
	public void testEach(){
		FCollection<A> c = prepareList();
		c.each(new Consumer<A>(){
			public void apply(A e) {
				e.i = e.i + 10;
			}
		});
		
		Iterator<A> it = c.iterator();
		assertEquals(18, it.next().i);
		assertEquals(22, it.next().i);
		assertEquals(244, it.next().i);
	}
	
	@Test
    public void testEachIndex(){
        FCollection<A> c = prepareList();
        
        final FList<Integer> is = FLists.newEmptyList();
        c.eachIndex(new Consumer<Integer>() {
            @Override
            public void apply(Integer e) {
                is.add(e);
            }
        });
        
        assertTrue(is.size() == 3);
        assertTrue(0 == is.first());
        assertTrue(2 == is.last());
    }

	@Test
	public void testAt(){
		FList<A> c = prepareList();
		
		assertEquals(12, c.at(1).i);
		assertEquals(234, c.at(-1).i);
		assertEquals(12, c.at(-2).i);
	}
	
	@Test
	public void testCompact(){
		FList<A> c = prepareList();
		c.add(null);
		c.add(null);
		
		assertEquals(5, c.size());
		
		FCollection<A> c2 = c.compact();
		assertEquals(3, c2.size());
	}	
	
	@Test
	public void testFirstAndLast(){
		FList<A> c = prepareList();
		assertEquals(8, c.first().i);
		assertEquals(234, c.last().i);
		
		FList<A> emptyList = new FArrayList<A>();
		assertNull(emptyList.first());
		assertNull(emptyList.last());
		
	}
	
	@Test
	public void testDeleteIf(){
		FList<A> c = prepareList();
		c.deleteIf(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i == 8 || e.i == 234;
			}
		});
		assertEquals(1, c.size());

		FList<Integer> fi = FLists.create(10, 23, 56);
		assertEquals(2, fi.deleteIf(new Predicate<Integer>() {
            public boolean apply(Integer e) {
                return e > 50;
            }
        }).size());
	}	
	
	
	@Test
	public void testReject(){
		FList<A> c = prepareList();
		FList<A> result = c.reject(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i == 8 || e.i == 234;
			}
		});
		assertEquals(1, result.size());
		assertEquals(3, c.size());
	}	
	
	
	@Test
	public void testAny(){
		FList<A> c = prepareList();
		boolean hasI8 = c.any(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i == 8;
			}
		});
		boolean hasI1 = c.any(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i == 11;
			}
		});
		
		assertTrue(hasI8);
		assertFalse(hasI1);
	}		
	
	@Test
	public void testAll(){
		FList<A> c = prepareList();
		boolean isAllBiggerThan7 = c.all(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i > 7;
			}
		});
		boolean isAllBiggerThan20 = c.all(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i > 20;
			}
		});
		
		assertTrue(isAllBiggerThan7);
		assertFalse(isAllBiggerThan20);
	}		
	
	@Test
	public void testCount(){
		FList<A> c = prepareList();
		int count = c.count(new Predicate<A>(){
			public boolean apply(A e) {
				return e.i > 70;
			}
		});
		assertEquals(1, count);
	}		
	
	@Test
	public void testTop(){
		FList<A> c = prepareList();
		FList<A> top2 = c.top(2);
		assertEquals(2, top2.size());
		
		FList<A> top5 = c.top(5);
		assertEquals(3, top5.size());		
	}

	@Test
	public void testPushWithArray(){
		FList<Integer> list = FLists.create(1,2,3);
		assertEquals(3, list.size());
		list.push(4,5,6);
		assertEquals(6, list.size());
		assertTrue(5 == list.at(4));
		
		list.push((Integer[])null);
		assertEquals(6, list.size());
	}		

    @Test
    public void testPushWithList(){
        FList<Integer> list = FLists.create(1,2,3);
        assertEquals(3, list.size());
        list.push(FLists.create(4,5,6));
        assertEquals(6, list.size());
        assertTrue(5 == list.at(4));
        
        list.push((List<Integer>)null);
        assertEquals(6, list.size());
    }
	
    @Test
    public void testPlusWithList(){
        FList<Integer> list = FLists.create(1,2,3);
        assertEquals(3, list.size());
        
        FList<Integer> result = list.plus(FLists.create(4,5,6));
        assertEquals(3, list.size());
        assertEquals(6, result.size());
        assertTrue(5 == result.at(4));
       
        assertFalse(list == result);
       
        assertEquals(3, list.plus((List<Integer>)null).size());
    }

    @Test
    public void testPlusWithArray(){
        FList<Integer> list = FLists.create(1,2,3);
        assertEquals(3, list.size());
        
        FList<Integer> result = list.plus(4,5,6);
        assertEquals(3, list.size());
        assertEquals(6, result.size());
        assertTrue(5 == result.at(4));
       
        assertFalse(list == result);
       
        assertEquals(3, list.plus((Integer[])null).size());
    }
    
    @Test
	public void testSort(){
	    FList<Integer> list = FLists.create(2,3,1,5,6,4,8,7);
	    list = list.sort2(new Comparator<Integer>() {
            
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
	    assertTrue(1 == list.first());
	    assertTrue(8 == list.last());
	    assertTrue(5 == list.at(4));
        
	}
	
	@Test
    public void testSortBy(){
        FList<A> list = prepareList();
        list = list.sortBy(new Function<A, Integer>() {

            @Override
            public Integer apply(A e) {
                return -e.i;
            }
            
        });
        assertTrue(234 == list.first().i);
        assertTrue(8 == list.last().i);
        
    }
	
	@Test
	public void testFold(){
		FList<Integer> list = FLists.create(1,2,3);
		int result = list.fold(0, new FoldFunction<Integer, Integer>(){
			public Integer apply(Integer element, Integer init) {
				return element + init;
			}
		});
		assertEquals(6, result);
		
		list = FLists.create(1,2,3,4);
		result = list.fold(1, new FoldFunction<Integer, Integer>(){
		    public Integer apply(Integer element, Integer init) {
		        return element * init;
		    }
		});
		assertEquals(24, result);
		
		list = FLists.create(1,2,3,4);
		List<Integer> container = new ArrayList<Integer>();
		List<Integer> containerResult = list.fold(container, new FoldFunction<Integer, List<Integer>>(){
			public List<Integer> apply(Integer element, List<Integer> init) {
				init.add(element);
				return init;
			}
		});
		assertEquals(4, containerResult.size());
		
        
        list = FLists.create(1,2,3,4);
        FMap<Integer, Integer> resultMap = 
                list.fold(new FHashMap<Integer, Integer>(), new FoldFunction<Integer, FMap<Integer, Integer>>(){
            public FMap<Integer, Integer> apply(Integer element, FMap<Integer, Integer> init) {
                init.put(element, element*3);
                return init;
            }
        });
        assertEquals(3, (int)resultMap.get(1));
        assertEquals(9, (int)resultMap.get(3));
	}
	
	@Test
    public void testReduce(){
        FList<Integer> list = FLists.create(1,2,3);
        int result = list.reduce(new FoldFunction<Integer, Integer>(){
            public Integer apply(Integer element, Integer init) {
                return element + init;
            }
        });
        assertEquals(6, result);
        
        list = FLists.create(1,2,3);
        result = list.reduce(new FoldFunction<Integer, Integer>(){
            public Integer apply(Integer element, Integer init) {
                return element * init;
            }
        });
        assertEquals(6, result);
        
        list = FLists.create(3);
        result = list.reduce(new FoldFunction<Integer, Integer>(){
            public Integer apply(Integer element, Integer init) {
                return element * init;
            }
        });
        assertEquals(3, result);
	}
	
	@Test(expected=RuntimeException.class)
	public void testReduceOnEmptyList(){
	    FList<Integer> list = FLists.create();
        list.reduce(new FoldFunction<Integer, Integer>(){
            public Integer apply(Integer element, Integer init) {
                return element * init;
            }
        });
	}
	
	@Test
	public void testGroupBy(){
	    FList<Integer> list = $(0,1,2,3,4,5,6,7,8);
	    FMap<Integer, FList<Integer>> map = list.groupBy(new Function<Integer, Integer>() {
            public Integer apply(Integer e) {
                return e % 3;
            }
        });

	    assertTrue(3 == map.keySet().size());
	    assertTrue(map.get(0).size() == 3);
	    assertTrue(map.get(1).size() == 3);
	    assertTrue(map.get(2).contains(8));
	}
	
	@Test
	public void testSum(){
	    FList<A> list = prepareList();
	    int sum = list.sum(new Function<A, Integer>(){
            @Override
            public Integer apply(A e) {
                return e.i;
            }
	    });
	    assertEquals(254, sum);
	}
	

	private FList<A> prepareList() {
		List<A> list = new ArrayList<A>();
		list.add(new A(8));
		list.add(new A(12));
		list.add(new A(234));
		return FLists.create(list);
	}

}

class A{
	public int i;
	public A(int i){ this.i = i; }
}


class B{
	public int i;
	public B(int i){ this.i = i; }
}