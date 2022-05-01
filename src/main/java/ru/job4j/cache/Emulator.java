package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean runMenu = true;

    @SuppressWarnings("checkstyle:InnerAssignment")
    private static void resultEmulator() {
        System.out.print("Select : ");
        int select = scanner.nextInt();

        switch (select) {
            case 0 -> {
                System.out.print("Input path : ");
                String pathDirFileCache = scanner.next();
                dirFileCache = new DirFileCache(pathDirFileCache);
            }
            case 1 -> {
                System.out.print("Input filename : ");
                String nameFileToCache = scanner.next();
                dirFileCache.load(nameFileToCache);
            }
            case 2 -> {
                System.out.print("Input filename : ");
                String nameFileFromCache = scanner.next();
                System.out.println(dirFileCache.get(nameFileFromCache));
            }
            case 3 -> {
                runMenu = false;
            }
            default -> System.out.println("You are input wrong select, input true select");
        }
    }

    private void menu() {
        String[] menu = new String[]{
                "Input path of cache directory",
                "Input of name file to cache",
                "Input of name file from cache",
                "Exit"
        };

        while (runMenu) {
            for (int i = 0; i < menu.length; i++) {
                System.out.println(i + "-" + menu[i]);
            }
            resultEmulator();
        }
    }

    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.menu();
    }
}
