package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       byte b = 5;
       short s = 28;
       int i = 200;
       long l = 2678990000000000L;
       double d = 4999.8888888;
       float f = 34.84393939939399999f;
       boolean bo = false;
       char ch = 'A';

        LOG.debug("b : {}, s : {}, i : {} , l : {}, d : {}, f : {}, bo : {}, ch : {}", b, s, i, l, d, f, bo, ch);

    }
}
