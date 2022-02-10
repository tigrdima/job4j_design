package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null && storage.containsKey(id)) {
          storage.put(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null && storage.containsKey(id)) {
            storage.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        if (storage.get(id) != null) {
            return storage.get(id);
        }
        return null;
    }
}
