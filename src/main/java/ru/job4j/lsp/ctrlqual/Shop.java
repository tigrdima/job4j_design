package ru.job4j.lsp.ctrlqual;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    public final static double K1 = 25;
    public final static double K2 = 75;
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = accept(food);
        double percent = analysis(food);

           if (rsl) {
               if (percent > K2 && percent < 100) {
                   food.setPrice(food.getPrice() * food.getDiscount());
               }
               foods.add(food);
           }
            return rsl;
    }

    @Override
    public boolean accept(Food food) {
        double percent = analysis(food);
        return percent >= K1 && percent < 100;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }
}
