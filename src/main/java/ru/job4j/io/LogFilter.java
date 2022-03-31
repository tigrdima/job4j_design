package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFilter {
    private List<String> errorFilter = new ArrayList<>();

    public List<String> filter(String file) {
        Pattern lineFilter = Pattern.compile("[4][0][4]\\s.+$");

        try (FileReader reader = new FileReader(file); BufferedReader in = new BufferedReader(reader)) {
            in.lines().forEach(line -> {
                Matcher matcher = lineFilter.matcher(line);
                if (matcher.find()) {
                    errorFilter.add(line);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorFilter;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            log.forEach(s -> out.printf("%s%n", s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }
    }
