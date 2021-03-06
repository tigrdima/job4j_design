package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("c:\\projects");
        search(start).forEach(System.out::println);
    }

    public static List<Path> search(Path start) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(start, duplicatesVisitor);
        return duplicatesVisitor.pathsDuplicatesFiles();
    }

}
