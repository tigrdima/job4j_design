package ru.job4j.lsp.parking;

public class AnalysisParking implements AccountingParking {

    Parking parking;

    public AnalysisParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public boolean addCarParking(Car car) {
        return false;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public int spaceParkingPlace(Car car) {
        return 0;
    }
}
