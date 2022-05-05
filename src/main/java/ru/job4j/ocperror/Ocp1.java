package ru.job4j.ocperror;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ocp1 {

    public String edit(File file) throws IOException {
        String fromFile = Files.readString(Path.of(file.getPath()));
        return fromFile.replace("A", "a");
    }
}
