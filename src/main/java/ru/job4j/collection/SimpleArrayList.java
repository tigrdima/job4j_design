package ru.job4j.collection;

import java.lang.reflect.Array;
import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;

    private T[] containerOld;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        modCount++;
        containerOld = Arrays.copyOf(container, size);
        Array.set(container, index, newValue);
        return containerOld[index];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        containerOld = Arrays.copyOf(container, size);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return containerOld[index];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }
        };
    }
}
