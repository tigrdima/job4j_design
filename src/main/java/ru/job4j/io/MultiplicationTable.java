package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        int[][] m =  new int[9][9];

        try (FileOutputStream out = new FileOutputStream("multiplication_table.txt")) {
            out.write("Multiplication table".getBytes());
            out.write(ln.getBytes());
            for (int row = 0; row < m.length; row++) {
                for (int cell = 0; cell < m.length; cell++) {
                    out.write(String.valueOf((row + 1) * (cell + 1)).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(ln.getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
