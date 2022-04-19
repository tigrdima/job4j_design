package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    static final Gson JSONCAR = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {
        final Car car = new Car(new RegNumberCar("678FGH"), "Audi", new String[]{"Abs", "A/C", "Leather interior"},
                true, 2000);
        final String carJson = JSONCAR.toJson(car);
        System.out.println(carJson);
        final Car carMod = JSONCAR.fromJson(carJson, Car.class);
        System.out.println(carMod);

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car carMod2 = (Car) unmarshaller.unmarshal(reader);
            System.out.println(carMod2);
        }
    }
}
