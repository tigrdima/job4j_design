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
        int sizeAuto = auto.getSize();
        boolean freePlaces = autos.length - numberAutoPlaces >= CarPassenger.SIZE;

        if (freePlaces) {
            if (sizeAuto == CarPassenger.SIZE && numbersParkingPassCar >= CarPassenger.SIZE) {
                autos[numberAutoPlaces++] = auto;
                numbersParkingPassCar--;
                rsl = true;

            } else if (sizeAuto > CarPassenger.SIZE && numbersParkingCargoCar >= CarPassenger.SIZE) {
                autos[numberAutoPlaces++] = auto;
                numbersParkingCargoCar--;
                rsl = true;

            } else if (sizeAuto > CarPassenger.SIZE && numbersParkingPassCar >= sizeAuto) {
                autos[numberAutoPlaces] = auto;
                numberAutoPlaces += sizeAuto;
                numbersParkingPassCar -= sizeAuto;
                rsl = true;
            }
        }
        return rsl;
    }
}