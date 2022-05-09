package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final static double K1 = 25;
    private final static double K2 = 75;
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
        double percent = analysis(food);
        boolean rsl = percent >= K1 && percent < 100;

        if (rsl && percent > K2) {
            food.setDiscount(0.5);
            food.setPrice(food.getPrice() * food.getDiscount());
        }
        return rsl;
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
