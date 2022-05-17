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
        int minSizeAuto = CarPassenger.SIZE;
        boolean freePlaces = autos.length - numberAutoPlaces >= minSizeAuto;

        if (freePlaces) {
            if (sizeAuto == minSizeAuto && numbersParkingPassCar >= minSizeAuto) {
                autos[numberAutoPlaces++] = auto;
                numbersParkingPassCar--;
                rsl = true;

            } else if (sizeAuto > minSizeAuto) {
                if (numbersParkingCargoCar >= minSizeAuto) {
                    autos[numberAutoPlaces++] = auto;
                    numbersParkingCargoCar--;
                    rsl = true;

                } else if (numbersParkingPassCar >= sizeAuto) {
                    autos[numberAutoPlaces] = auto;
                    numberAutoPlaces += sizeAuto;
                    numbersParkingPassCar -= sizeAuto;
                    rsl = true;
                }
            }
        }
        return rsl;
    }
}