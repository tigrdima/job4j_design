package ru.job4j.collection;

public interface List<T> extends Iterable<T> {
    void add(T value);

    void sizeArray(int size);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
