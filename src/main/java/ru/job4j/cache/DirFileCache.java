package ru.job4j.cache;

import net.sf.saxon.trans.SymbolicName;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
    File file = new File(cachingDir + "\\" + key);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader.lines().forEach(stringBuilder::append);
            String str = stringBuilder.toString();

            put(key, str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return get(key);
    }
}
