package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegNumberCar {

    @XmlAttribute
    private String number;

    public RegNumberCar() {
    }

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
