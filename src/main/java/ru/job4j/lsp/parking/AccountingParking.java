package ru.job4j.lsp.parking;

public interface AccountingParking {

    boolean addCarParking(Car car);

    boolean park(Car car);

    int spaceParkingPlace(Car car);

    default int analysis(Car car) {
        return 0;
    }
}
