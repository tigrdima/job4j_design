package ru.job4j.lsp.parking;

public class CarPassenger implements Auto {
    private String name;
    private int size;

    public CarPassenger(String name) {
        this.name = name;
    }

    @Override
    public void setSize() {
        this.size = 1;
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
