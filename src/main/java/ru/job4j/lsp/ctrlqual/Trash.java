package ru.job4j.lsp.ctrlqual;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    public final static double K2 = 100;
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
        return analysis(food) >= K2;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }
}
