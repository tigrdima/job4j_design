package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {

        if (in.isEmpty() && out.isEmpty()) {
           throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        T rsl = null;
        if (!out.isEmpty()) {
                rsl = out.pop();
                size--;
            }
        return rsl;
    }

    public void push(T value) {
            in.push(value);
            size++;
    }

    public int size() {
        return size;
    }
}
