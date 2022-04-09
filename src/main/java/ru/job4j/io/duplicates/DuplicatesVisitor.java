package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> allFiles = new HashMap<>();
    private final List<Path> pathsOfDuplicatesFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        Path path = file.toAbsolutePath();

        if (allFiles.containsKey(fileProperty)) {
            allFiles.get(fileProperty).add(path);
        }
        allFiles.putIfAbsent(fileProperty, new ArrayList<>(List.of(path)));

        return super.visitFile(file, attrs);
    }

    public List<Path> pathsDuplicatesFiles() {
        allFiles.forEach((key, value) -> {
            if (value.size() > 1) {
                pathsOfDuplicatesFiles.addAll(value);
            }
        });
        return pathsOfDuplicatesFiles;
    }
}


