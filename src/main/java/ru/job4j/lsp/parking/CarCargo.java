package ru.job4j.lsp.parking;

public class CarCargo implements Auto {
    private String name;
    private int size;

    public CarCargo(String name) {
        this.name = name;
    }

    @Override
    public void setSize() {
        this.size = 2;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean acceptPark(Parking parking) {
        return false;
    }
}
