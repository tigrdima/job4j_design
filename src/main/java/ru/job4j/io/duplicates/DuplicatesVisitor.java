package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<Path, FileProperty> allFiles = new HashMap<>();
    private final Set<FileProperty> nonDuplicatesFiles = new HashSet<>();
    private final List<Path> pathsOfDuplicatesFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());

        allFiles.put(file.toAbsolutePath(), fileProperty);
        if (!nonDuplicatesFiles.add(fileProperty)) {
            nonDuplicatesFiles.remove(fileProperty);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> pathsDuplicatesFiles() {
        allFiles.forEach((key, value) -> {
            if (!nonDuplicatesFiles.contains(value)) {
                pathsOfDuplicatesFiles.add(key);
            }
        });
        return pathsOfDuplicatesFiles;
    }
}


