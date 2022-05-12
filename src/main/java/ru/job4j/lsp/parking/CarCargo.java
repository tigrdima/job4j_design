package ru.job4j.lsp.parking;

public class CarCargo implements Auto {
    private String name;
    private final int size;

    public CarCargo(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }
}
