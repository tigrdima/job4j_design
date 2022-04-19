package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    static  final Gson JSONCAR = new GsonBuilder().create();

    public static void main(String[] args) {
       final Car car = new Car(new RegNumberCar("678FGH"), "Audi", new String[] {"Abs", "A/C", "Leather interior"},
               true, 2000);

        final String carJson = JSONCAR.toJson(car);
        System.out.println(carJson);

        final Car carMod = JSONCAR.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
