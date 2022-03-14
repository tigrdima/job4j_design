package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map1<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private final int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        expand();
        int index = indexFor(hash(key.hashCode()));

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        } else if (equalsHash(key)) {
            table[index].value = value;
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private boolean equalsHash(K key) {
        int index = indexFor(hash(key.hashCode()));
        return hash(table[index].key.hashCode()) == hash(key.hashCode()) && table[index].key.equals(key);
    }

    private void expand() {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            table = new MapEntry[(int) (capacity + (capacity * LOAD_FACTOR))];
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key.hashCode()));

        if (table[index] != null && equalsHash(key)) {
            rsl = table[index].value;
            modCount++;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));

        if (table[index] != null && equalsHash(key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length - 1 && table[index] == null) {
                    index++;
                }
                return index < table.length - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
