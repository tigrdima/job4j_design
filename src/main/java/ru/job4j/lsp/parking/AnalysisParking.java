package ru.job4j.lsp.parking;

public class AnalysisParking implements Parking {
    int numbersParkingPassCar;
    int numberParkingCargoCar;
    private Auto auto;
    private String[] parkPlace;

    public AnalysisParking(int numbersParkingPassCar, int numberParkingCargoCar, Auto auto) {
        this.numbersParkingPassCar = numbersParkingPassCar;
        this.numberParkingCargoCar = numberParkingCargoCar;
        this.auto = auto;
    }

    @Override
    public boolean add(Auto auto) {
        return false;
    }
}
