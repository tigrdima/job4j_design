package ru.job4j.lsp.parking;

public class AnalysisParking implements Parking {
    private final int numbersParkingPassCar;
    private final int numberParkingCargoCar;
    private final String[] parkPlace;

    public AnalysisParking(int numbersParkingPassCar, int numberParkingCargoCar) {
        this.numbersParkingPassCar = numbersParkingPassCar;
        this.numberParkingCargoCar = numberParkingCargoCar;
        this.parkPlace = new String[numberParkingCargoCar + numbersParkingPassCar];
    }

    @Override
    public boolean add(Auto auto) {
        return false;
    }
}
