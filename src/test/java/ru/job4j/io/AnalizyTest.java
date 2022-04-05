package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.csv");
        File target = folder.newFile("target.csv");

        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:55:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
             in.lines().forEach(line -> rsl.append(line).append(" ; "));
        }
        assertThat(rsl.toString(), is("10:57:01 ; 10:59:01 ; 11:01:02 ; 11:02:02 ; "));
    }
}