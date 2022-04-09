package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validate(args);

        Path start = Paths.get(args[0]);
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(args[1]);

        search(start, predicate).forEach(System.out::println);
    }

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Arguments not found: 1 - Root folder. Usage java -jar dir.jar ROOT_FOLDER., "
                                                + "2 - File extension to look for");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The file extension must start with \".\"");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
