package ru.job4j.lsp.parking;

public class AnalysisParking implements Parking {
    private int numbersParkingPassCar;
    private int numbersParkingCargoCar;
    private final Auto[] autos;
    private int numberAutoPlaces = 0;

    public AnalysisParking(int numbersParkingPassCar, int numbersParkingCargoCar) {
        this.numbersParkingPassCar = numbersParkingPassCar;
        this.numbersParkingCargoCar = numbersParkingCargoCar;
        this.autos = new Auto[numbersParkingCargoCar + numbersParkingPassCar];
    }

    @Override
    public boolean add(Auto auto) {
        boolean rsl = false;
        int size = auto.getSize();

        if (size == 1 && numbersParkingPassCar > 0) {
            autos[numberAutoPlaces++] = auto;
            numbersParkingPassCar--;
            rsl = true;

        } else if (size > 1 && numbersParkingCargoCar > 0) {
                autos[numberAutoPlaces] = auto;
                numberAutoPlaces = size;
                numbersParkingCargoCar--;
                rsl = true;

            } else if (numbersParkingPassCar >= size) {
                autos[numberAutoPlaces] = auto;
                numberAutoPlaces = size;
                numbersParkingPassCar -= size;
                rsl = true;
            }
        return rsl;
    }
}
