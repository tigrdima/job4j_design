package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> v = new SoftReference<>(value);
        cache.putIfAbsent(key, v);
    }

    public V get(K key) {
        V rsl = null;
        SoftReference<V> value = cache.get(key);

       if (value != null) {
            V val = value.get();
            if (val != null) {
                rsl = val;
            }
        }
        return rsl;
    }

    protected abstract V load(K key);
}
