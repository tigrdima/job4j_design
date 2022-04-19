package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final Gson JSONCAR = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {
        final Car car = new Car(new RegNumberCar("678FGH"), "Audi", new String[]{"Abs", "A/C", "Leather interior"},
                true, 2000);
        final String carJson = JSONCAR.toJson(car);
        System.out.println("Serialization : " + carJson);

        final String fromStringToObject =
                "{"
                        + "\"regNumberCar\": {"
                        + "\"number\":\"085FYW\""
                        + "},"
                        + "\"name\":\"Lada\","
                        + "\"options\":"
                        + "[\"nonAbs\",\"nonA/C\",\"Fabric interior\"],"
                        + "\"automaticTransmission\": false,"
                        + "\"carMileage\":23450"
                        + "}";

        final Car carMod = JSONCAR.fromJson(fromStringToObject, Car.class);
        System.out.println("Deserialization : " + carMod + System.lineSeparator());

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

        RegNumberCar regNumberCar = new RegNumberCar();
        Car car2 = new Car();
        regNumberCar.setCar(car2);
        car2.setRegNumberCar(regNumberCar);

        System.out.println(new JSONObject(car2));

        JSONObject jsonRegNumberCar = new JSONObject("{\"number\":\"085FYW\"}");

        List<String> option = new ArrayList<>();
        option.add("nonAbs");
        option.add("nonA/C");
        option.add("Fabric interior");
        JSONArray jsonOptions = new JSONArray(option);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("regNumberCar", jsonRegNumberCar);
        jsonObject.put("name", car.getName());
        jsonObject.put("options", jsonOptions);
        jsonObject.put("automaticTransmission", car.isAutomaticTransmission());
        jsonObject.put("carMileage", car.getCarMileage());

        System.out.println(jsonObject);
        System.out.println(new JSONObject(car));
    }
}
