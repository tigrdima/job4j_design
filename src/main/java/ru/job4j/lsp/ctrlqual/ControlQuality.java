package ru.job4j.lsp.ctrlqual;

import java.util.*;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addStore(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.accept(food)) {
                    store.add(food);
                }
            }
        }
    }
}
