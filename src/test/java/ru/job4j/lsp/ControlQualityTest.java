package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.ctrlqual.*;

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
        List<Food> foods = List.of(
                new MilkFood("Колбаса", 10, new GregorianCalendar(2022, Calendar.MAY, 2), 450, 0.5),
                new MilkFood("Молоко", 4, new GregorianCalendar(2022, Calendar.MAY, 10), 20, 0.1),
                new MilkFood("Хлеб", 8, new GregorianCalendar(2022, Calendar.MAY, 7), 45, 0.4),
                new MilkFood("Сосиськи", 15, new GregorianCalendar(2022, Calendar.APRIL, 20), 45, 0.4)
        );

        List<Store> stores = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(foods);

        List<Food> warehouse = stores.get(0).getAll();
        List<Food> shop = stores.get(1).getAll();
        List<Food> trash = stores.get(2).getAll();

        assertTrue(warehouse.contains(foods.get(1)));
        assertFalse(shop.contains(foods.get(1)));
        assertFalse(trash.contains(foods.get(1)));
    }
}