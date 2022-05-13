package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalysisParkingTest {

    @Test
    public void addCarIsTrue() {
        Auto auto1 = new CarPassenger("p1");
        Auto auto2 = new CarPassenger("p2");
        Auto auto3 = new CarCargo("c1", 2);

        AnalysisParking parking = new AnalysisParking(2, 1);

        assertTrue(parking.add(auto1));
        assertTrue(parking.add(auto2));
        assertTrue(parking.add(auto3));
    }

    @Test
    public void addFalseWhenNoPlace() {
        Auto auto1 = new CarPassenger("p1");
        Auto auto2 = new CarPassenger("p2");
        Auto auto3 = new CarCargo("c1", 2);
        Auto auto4 = new CarCargo("c2", 2);

        AnalysisParking parking = new AnalysisParking(2, 1);
        assertTrue(parking.add(auto1));
        assertTrue(parking.add(auto2));
        assertTrue(parking.add(auto3));
        assertFalse(parking.add(auto4));

    }

    @Test
    public void addCarPassengerIsFalse() {
        Auto auto1 = new CarCargo("c1", 2);
        Auto auto2 = new CarCargo("c2", 2);
        Auto auto3 = new CarPassenger("p1");

        AnalysisParking parking = new AnalysisParking(2, 1);
        assertTrue(parking.add(auto1));
        assertTrue(parking.add(auto2));
        assertFalse(parking.add(auto3));
    }

    @Test
    public void addCarCargoIsFalse() {
        Auto auto1 = new CarCargo("c1", 2);
        Auto auto2 = new CarCargo("c2", 2);

        AnalysisParking parking = new AnalysisParking(1, 1);
        assertTrue(parking.add(auto1));
        assertFalse(parking.add(auto2));
    }
}