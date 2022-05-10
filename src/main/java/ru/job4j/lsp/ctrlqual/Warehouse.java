package ru.job4j.lsp.ctrlqual;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    public final static double K1 = 25;
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
       boolean rsl = accept(food);
        if (rsl) {
            foods.add(food);
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        return analysis(food) < K1;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }
}
