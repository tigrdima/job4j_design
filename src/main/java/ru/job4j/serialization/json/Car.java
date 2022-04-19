package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final RegNumberCar regNumberCar;
    private final String name;
    private final String[] options;
    private final boolean automaticTransmission;
    private final int carMileage;

    public Car(RegNumberCar regNumberCar, String name, String[] options, boolean automaticTransmission, int carMileage) {
        this.regNumberCar = regNumberCar;
        this.name = name;
        this.options = options;
        this.automaticTransmission = automaticTransmission;
        this.carMileage = carMileage;
    }

    @Override
    public String toString() {
        return "Car{"
                + "regNumberCar=" + regNumberCar
                + ", name='" + name + '\''
                + ", options=" + Arrays.toString(options)
                + ", automaticTransmission=" + automaticTransmission
                + ", carMileage=" + carMileage
                + '}';
    }
}
