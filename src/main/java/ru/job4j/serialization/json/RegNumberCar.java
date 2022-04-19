package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegNumberCar {

    private Car car;

    @JSONPropertyIgnore
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
         this.car = car;
    }

    @XmlAttribute
    private String number;

    public RegNumberCar() {
    }

    public RegNumberCar(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "RegNumberCar{"
                + "number='" + number + '\''
                + '}';
    }
}
