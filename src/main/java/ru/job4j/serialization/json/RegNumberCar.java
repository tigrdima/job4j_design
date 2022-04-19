package ru.job4j.serialization.json;

public class RegNumberCar {
    private final String number;

    public RegNumberCar(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "RegNumberCar{"
                + "number='" + number + '\''
                + '}';
    }
}
