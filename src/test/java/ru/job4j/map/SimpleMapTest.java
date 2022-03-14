package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void put() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("Dima", "Bike"));
        assertTrue(simpleMap.put("Dima", null));
        assertTrue(simpleMap.put("Petr", "Car"));
        assertFalse(simpleMap.put("Vasya", "Car"));
        assertTrue(simpleMap.put("Olya", "Car"));
        assertTrue(simpleMap.put("Tanya", "Car"));
        assertFalse(simpleMap.put("Boris", "Car"));
        assertTrue(simpleMap.put("Egor", "Car"));
    }

    @Test
    public void get() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("Dima", null);
        assertNull(simpleMap.get("Dima"));
        assertNotEquals("Bike", simpleMap.get("Dima"));
    }

    @Test
    public void getEmptyMap() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertNull(simpleMap.get("Dima"));
    }

    @Test
    public void remove() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.remove("Dima"));
        simpleMap.put("Dima", "Car");
        assertTrue(simpleMap.remove("Dima"));
        simpleMap.put("Dima", "Car");
        assertFalse(simpleMap.remove("Petr"));
    }

    @Test
    public void removeEmptyMap() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.remove("Dima"));
    }
    
    @Test
    public void whenIteratorFromEmptyThenHasNextReturnFalse() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyThenNextThrowException() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("Dima", "Bike");
        simpleMap.put("Petr", "Car");
        assertEquals("Dima", simpleMap.iterator().next());
        assertEquals("Dima", simpleMap.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("Dima", "Bike");
        simpleMap.put("Petr", "Car");

        Iterator<String> iterator = simpleMap.iterator();

        assertEquals("Dima", iterator.next());
        assertEquals("Petr", iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("Dima", "Bike");
        simpleMap.put("Petr", "Car");
        Iterator<String> iterator = simpleMap.iterator();
        simpleMap.put("Olya", "Car");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("Dima", "Bike");
        simpleMap.put("Petr", "Car");
        Iterator<String> iterator = simpleMap.iterator();
        simpleMap.remove("Petr");
        iterator.next();
    }
}