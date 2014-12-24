package org.agilej.fava.test;

import static org.junit.Assert.*;

import org.agilej.fava.Consumer;
import org.agilej.fava.FList;
import org.agilej.fava.Times;
import org.agilej.fava.util.FLists;
import org.junit.Test;

public class TimesTest {

    @Test
    public void testCreate() {
        Times t = Times.of(5);
        assertNotNull(t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithException() {
        Times.of(-1);
    }

    @Test
    public void testEach() {
        final FList<Integer> array = FLists.newEmptyList();
        Times.of(5).each(new Consumer<Integer>() {
            public void apply(Integer e) {
                array.add(e);
            }
        });
        assertEquals(5, array.size());
        assertTrue(0 == array.first());
        assertTrue(4 == array.last());
    }
}
