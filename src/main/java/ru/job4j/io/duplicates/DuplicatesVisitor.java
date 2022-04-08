package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<Path, FileProperty> allFiles = new HashMap<>();
    private final Set<FileProperty> setFiles = new HashSet<>();
    private final List<FileProperty> duplicatesFiles = new ArrayList<>();
    private final List<Path> pathsOfDuplicatesFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());

        allFiles.put(file.toAbsolutePath(), fileProperty);
        if (!setFiles.add(fileProperty)) {
            duplicatesFiles.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> pathsDuplicatesFiles() {
        setFiles.retainAll(duplicatesFiles);
        allFiles.forEach((key, value) -> {
            if (setFiles.contains(value)) {
                pathsOfDuplicatesFiles.add(key);
            }
        });
        return pathsOfDuplicatesFiles;
    }
}


