package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.ctrlqual.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void foodPriceChange() {
        List<Food> foods = List.of(
                new MilkFood("Колбаса", 10, new GregorianCalendar(2022, Calendar.MAY, 2), 450, 0.5)
        );

        List<Store> stores = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(foods);

        double priceChange = stores.get(1).getAll().get(0).getPrice();
        assertThat(priceChange, is(225.0));
    }

    @Test
    public void foodDistribution() {
        Food food = new Sausage("Колбаса", 10, new GregorianCalendar(2022, Calendar.MAY, 2), 450, 0.5);
        Food food1 =  new MilkFood("Молоко", 4, new GregorianCalendar(2022, Calendar.MAY, 11), 20, 0.1);
        Food food2 =  new Bread("Хлеб", 8, new GregorianCalendar(2022, Calendar.MAY, 8), 45, 0.4);
        Food food3 = new Sausage("Сосиськи", 15, new GregorianCalendar(2022, Calendar.APRIL, 20), 45, 0.4);

        List<Food> warehouseList = List.of(food1);
        List<Store> stores = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality warehouse = new ControlQuality(stores);
        warehouse.addStore(warehouseList);
        assertThat(warehouseList, is(stores.get(0).getAll()));
        assertTrue(stores.get(1).getAll().isEmpty());
        assertTrue(stores.get(2).getAll().isEmpty());

        List<Food> shopList = List.of(food, food2);
        List<Store> stores2 = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality shop = new ControlQuality(stores2);
        shop.addStore(shopList);
        assertThat(shopList, is(stores2.get(1).getAll()));
        assertTrue(stores2.get(0).getAll().isEmpty());
        assertTrue(stores2.get(2).getAll().isEmpty());

        List<Food> trashList = List.of(food3);
        List<Store> stores3 = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality trash = new ControlQuality(stores3);
        trash.addStore(trashList);
        assertThat(trashList, is(stores3.get(2).getAll()));
        assertTrue(stores3.get(0).getAll().isEmpty());
        assertTrue(stores3.get(1).getAll().isEmpty());
    }
}