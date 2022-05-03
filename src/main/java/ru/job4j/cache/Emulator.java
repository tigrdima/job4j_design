package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean runMenu = true;

    private static void resultEmulator() throws IOException {
        System.out.print("Select : ");
        int select = scanner.nextInt();

        switch (select) {
            case 0:
                System.out.print("Input path : ");
                String pathDirFileCache = scanner.next();
                dirFileCache = new DirFileCache(pathDirFileCache);
                break;
            case 1:
                System.out.print("Input filename : ");
                String nameFileToCache = scanner.next();
                String str = dirFileCache.load(nameFileToCache);
                dirFileCache.put(nameFileToCache, str);
                break;
            case 2:
                System.out.print("Input filename : ");
                String nameFileFromCache = scanner.next();
                System.out.println(dirFileCache.get(nameFileFromCache));
                break;
            case 3:
                runMenu = false;
                break;
            default:
                System.out.println("You are input wrong select, input true select");
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
            try {
                resultEmulator();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.menu();
    }
}
