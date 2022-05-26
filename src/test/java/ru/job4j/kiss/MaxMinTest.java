package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMin() {
        MaxMin<String> maxMin = new MaxMin<>();
        List<String> list = List.of(
                "Иванов",
                "Кузнецов",
                "Ли"
        );
        Comparator<String> comparator = String::compareTo;
        String expect = "Иванов";

        assertThat(maxMin.min(list, comparator), is(expect));
    }

    @Test
    public void whenMax() {
        MaxMin<Integer> maxMin = new MaxMin<>();
        List<Integer> list = List.of(8, 12, 7);
        Comparator<Integer> comparator = Integer::compareTo;
        int expect = 12;

        assertThat(maxMin.max(list, comparator), is(expect));
    }
}