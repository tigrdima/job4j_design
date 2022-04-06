package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizy {
    private String start;
    private String end;
    private boolean count;
    private boolean rsl;

    public void unavailable(String source, String target) {

        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {

            Pattern pattern = Pattern.compile("^500|^400");

            in.lines().forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                rsl = matcher.find();
                String[] s = line.split(" ");

                if (rsl && !count) {
                    start = s[1];
                    count = true;
                    return;
                } else if (rsl || !count) {
                    return;
                }
                count = false;
                end = s[1];
                out.printf("%s;%s;%n", start, end);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("logServer.csv", "unavailable.csv");
    }
}
