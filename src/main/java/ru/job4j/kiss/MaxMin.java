package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin<T> {

    public T max(List<T> value, Comparator<T> comparator) {
        return rsl(value, ((v1, v2) -> comparator.compare(v1, v2) > 0));
    }

    public T min(List<T> value, Comparator<T> comparator) {
      return rsl(value, ((v1, v2) -> comparator.compare(v1, v2) < 0));
    }

    private T rsl(List<T> value, BiPredicate<T, T> predicate) {
        T rsl = value.get(0);
        for (T val : value) {
            if (predicate.test(val, rsl)) {
                rsl = val;
            }
        }
        return rsl;
    }
}
