package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final static double K2 = 100;
    List<Food> foods = new ArrayList<>();

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
        return foods;
    }

    @Override
    public double analysis(Food food) {
        return Store.super.analysis(food);
    }
}
