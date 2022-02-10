package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddFirstAndDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test
    public void whenAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(1);
        linked.addFirst(2);
        linked.addFirst(3);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
        assertTrue(it.hasNext());
        assertThat(linked.size(), is(3));
    }
}