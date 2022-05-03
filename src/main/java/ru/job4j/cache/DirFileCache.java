package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String str = null;

        try {
            str = Files.readString(Path.of(cachingDir, key));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
