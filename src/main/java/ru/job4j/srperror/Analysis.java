package ru.job4j.srperror;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Analysis {

    public String read(File file) throws IOException {
        return Files.readString(Path.of("c:\\1.txt"));
    }

    public void save(String str) {
        String[] strings = str.split(System.lineSeparator());
    }

    public void output(String[] str) {
        System.out.println(Arrays.toString(str));
    }
}
