package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;

            while ((read = in.read()) != -1) {
                char a = (char) read;
                text.append(a);
            }
            String[] n = text.toString().split(System.lineSeparator());

            for (String s : n) {
                boolean rsl = Integer.parseInt(s) % 2 == 0;
                System.out.println(s + " - Even " + rsl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
