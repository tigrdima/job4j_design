package ru.job4j.lsp.ctrlqual;

import java.util.Calendar;
import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean accept(Food food);

    List<Food> getAll();

    default double analysis(Food food) {
            Calendar now = Calendar.getInstance();
            Calendar calendar = food.getCreateDate();
            float l = now.getTimeInMillis();
            float l1 = calendar.getTimeInMillis();
            float rsl = (float) ((l - l1) / 9.074e+7);
            return (rsl / food.getExpiryDate()) * 100;
    }
}
