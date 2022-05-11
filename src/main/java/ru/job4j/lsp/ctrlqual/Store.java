package ru.job4j.lsp.ctrlqual;

import java.util.Calendar;
import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean accept(Food food);

    List<Food> getAll();

    default double analysis(Food food) {
        double now = Calendar.getInstance().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double max = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();

        return Math.rint((now / max) * 100);
    }
}
