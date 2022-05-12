package ru.job4j.lsp.parking;

public interface Auto {

    void setSize();

    int getSize();

    String getName();

    boolean acceptPark(Parking parking);
}
