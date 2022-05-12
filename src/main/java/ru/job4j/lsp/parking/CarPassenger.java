package ru.job4j.lsp.parking;

public class CarPassenger implements Auto {
    private String name;
    public static final int SIZE = 1;

    public CarPassenger(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getName() {
        return name;
    }
}
