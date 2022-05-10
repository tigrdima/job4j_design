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

    public static void main(String[] args) {
        List<Food> foods = new ArrayList<>();
        List<Store> stores = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar milk = Calendar.getInstance();
        milk.set(2022, Calendar.APRIL, 30);

        Food milk1 = new MilkFood("M", 10, milk, 20, 1);
        Food milk2 = new MilkFood("M1", 3, new GregorianCalendar(2022, Calendar.APRIL, 30), 20, 1);

        foods.add(milk1);
        System.out.println(milk1.getCreateDate().getTime());
        foods.add(milk2);
        ControlQuality controlQuality = new ControlQuality(stores);

        controlQuality.addStore(foods);
        System.out.println("Warehouse - ");
        stores.get(0).getAll().forEach(System.out::println);
        System.out.println("Shop - ");
        stores.get(1).getAll().forEach(System.out::println);
        System.out.println("Trash - ");
        stores.get(2).getAll().forEach(System.out::println);
    }
}
