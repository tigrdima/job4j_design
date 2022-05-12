package ru.job4j.lsp.parking;

public class AnalysisParking implements AccountingParking {

    private Auto auto;
    private Parking parking;
    private String[] parkPlace;

    public AnalysisParking(Auto auto, Parking parking) {
        this.auto = auto;
        this.parking = parking;
    }

    @Override
    public boolean add(Auto auto) {
        return false;
    }
}
