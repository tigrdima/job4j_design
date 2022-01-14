package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        if (row < data.length) {
            while (data[row].length == 0) {
                if (row == data.length - 1) {
                    return false;
                } else {
                    row++;
                    column = 0;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return data[row][column++];
        }
    }
}

