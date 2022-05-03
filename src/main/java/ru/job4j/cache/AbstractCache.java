package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> v = new SoftReference<>(value);
        cache.putIfAbsent(key, v);
    }

    public V get(K key) throws IOException {

        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
            if (value == null) {
                value = load(key);
                cache.put(key, new SoftReference<>(value));
            }

        return value;
    }

    protected abstract V load(K key) throws IOException;
}
