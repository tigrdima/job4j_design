package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        File path = new File(argsName.get("path"));
        if (!path.exists()) {
            throw new IllegalArgumentException(path + " - file not found");
        }
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = String.join(delimiter, Arrays.asList(argsName.get("filter").split(",")));

        List<String> indexFilterColumn = new ArrayList<>();
        List<String> filterCVS = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            List<String> firstLine = Arrays.asList(scanner.nextLine().split(delimiter));
            filterCVS.add(filter);
            int i = 0;
            while (i < firstLine.size()) {
                if (filter.contains(firstLine.get(i))) {
                    indexFilterColumn.add(String.valueOf(firstLine.indexOf(firstLine.get(i))));
                }
                i++;
            }

            while (scanner.hasNext()) {
                String[] s1 = scanner.nextLine().split(delimiter);
                int j = 0;
                List<String> lineFilterCVS = new ArrayList<>();
                while (j < indexFilterColumn.size()) {
                    lineFilterCVS.add(s1[Integer.parseInt(indexFilterColumn.get(j))]);
                    j++;
                }
                filterCVS.add(String.join(delimiter, lineFilterCVS));
            }

            if (out.equals("stdout")) {
                filterCVS.forEach(System.out::println);
            } else {
               try (PrintWriter outFile = new PrintWriter(new FileWriter(out))) {
                   filterCVS.forEach(outFile::println);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not all arguments are present");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);

        ArgsName cvs = ArgsName.of(args);
        try {
            handle(cvs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
