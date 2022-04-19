package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    private RegNumberCar regNumberCar;
    @XmlAttribute
    private String name;

    @XmlElementWrapper
    @XmlElement(name = "option")
    private String[] options;
    @XmlAttribute
    private boolean automaticTransmission;
    @XmlAttribute
    private int carMileage;

    public Car() {
    }

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
