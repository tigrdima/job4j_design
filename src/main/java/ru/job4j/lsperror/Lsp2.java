package ru.job4j.lsperror;

public class Lsp2 {

    public static Bird getFly() {
        return new Duck();
    }

    public static void main(String[] args) {
        System.out.println(getFly().fly());
    }
}

class Bird {
    public boolean fly() {
        return true;
    }
}

class Eagle extends Bird {
}

class Duck extends Bird {
    @Override
    public boolean fly() {
        return false;
    }
}
