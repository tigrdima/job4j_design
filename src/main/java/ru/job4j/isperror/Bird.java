package ru.job4j.isperror;

import java.util.List;

public interface Bird {

    List<Bird> add(Bird bird);

    void edit(List<Bird> birds);

    void setHeightFly(int height, Bird bird);

}
