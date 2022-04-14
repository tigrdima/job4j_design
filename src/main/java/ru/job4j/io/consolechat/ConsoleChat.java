package ru.job4j.io.consolechat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> log = new ArrayList<>();
        String botAnswer;
        String botName = "Бот";
        String userName = "Вася";

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print(userName + " : ");
        String im = scanner.nextLine();
        String imAsk = im.equals(STOP) ? STOP : im.equals(OUT) ? OUT : CONTINUE;

        while (!imAsk.equals(OUT)) {
            if (imAsk.equals(CONTINUE)) {
                log.add(userName + " : " + im);
                int randomAnswer = random.nextInt(readPhrases().size() - 1);
                botAnswer = botName + " : " + readPhrases().get(randomAnswer);
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            while (imAsk.equals(STOP)) {
                log.add(userName + " : " + im);
                System.out.print(userName + " : ");
                im = scanner.nextLine();
                if (im.equals(CONTINUE)) {
                    log.add(userName + " : " + im);
                    botAnswer = botName + " : " + "продолжим";
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                    imAsk = CONTINUE;
                } else if (im.equals(OUT)) {
                    imAsk = OUT;
                }
            }
            if (im.equals(OUT)) {
                break;
            }
            System.out.print(userName + " : ");
            im = scanner.nextLine();
            imAsk = im.equals(STOP) ? STOP : im.equals(OUT) ? OUT : CONTINUE;
        }
        log.add(userName + " : " + im);
        System.out.println(botName + " : " + "До свидания");
        botAnswer = botName + " : До свидания";
        log.add(botAnswer);
        saveLog(log);
    }

    private List<String> readPhrases() throws IOException {
        List<String> read = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(read::add);
        }
        return read;
    }

    private void saveLog(List<String> log) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        }
    }

    public static void main(String[] args) throws IOException {
        String path = ".\\src\\main\\java\\ru\\job4j\\io\\consolechat\\log.txt";
        String botAnswers = ".\\src\\main\\java\\ru\\job4j\\io\\consolechat\\botAnswers.txt";

        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.run();
    }
}
