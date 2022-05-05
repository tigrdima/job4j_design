package ru.job4j.srperror;

import java.io.File;

public interface Srp1 {

   String read(File file);

    void sendByEmail(File file);

    void outputPrinter(File file);
}
