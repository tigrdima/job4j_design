package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.ctrlqual.*;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void foodPriceChange() {
        Calendar createDate = Calendar.getInstance();
        Calendar expiryDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 9, 0, 0);
        expiryDate.set(expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 1, 0, 0);

        Food food = new MilkFood("Колбаса", expiryDate, createDate, 450, 0.5);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(List.of(food));

        assertThat(food.getPrice(), is(225.0));
    }

    @Test
    public void foodWarehouseDistribution() {
        Calendar createDate = Calendar.getInstance();
        Calendar expiryDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH), -1, 0, 0);
        expiryDate.set(expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 5, 0, 0);

        Food food = new MilkFood("Молоко", expiryDate, createDate, 20, 0.1);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(List.of(food));

        assertThat(food, is(warehouse.getAll().get(0)));
        assertTrue(shop.getAll().isEmpty());
        assertTrue(trash.getAll().isEmpty());
    }

    @Test
    public void foodShopDistribution() {
        Calendar createDate = Calendar.getInstance();
        Calendar expiryDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 3, 0, 0);
        expiryDate.set(expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 8, 0, 0);

        Food food = new Bread("Хлеб", expiryDate, createDate, 45, 0.4);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(List.of(food));
        assertThat(food, is(shop.getAll().get(0)));
        assertTrue(warehouse.getAll().isEmpty());
        assertTrue(trash.getAll().isEmpty());
    }

    @Test
    public void foodTrashDistribution() {
        Calendar createDate = Calendar.getInstance();
        Calendar expiryDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 20, 0, 0);
        expiryDate.set(expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 5, 0, 0);

        Food food = new Sausage("Сосиски", expiryDate, createDate, 45, 0.4);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.addStore(List.of(food));
        assertThat(food, is(trash.getAll().get(0)));
        assertTrue(shop.getAll().isEmpty());
        assertTrue(warehouse.getAll().isEmpty());
    }
}