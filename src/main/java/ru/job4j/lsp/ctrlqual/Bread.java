package ru.job4j.lsp.ctrlqual;

import java.util.Calendar;

public class Bread extends Food {

    public Bread(String name, int expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
