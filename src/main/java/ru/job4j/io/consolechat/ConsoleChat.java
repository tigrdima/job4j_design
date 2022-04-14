package ru.job4j.io.consolechat;

import java.io.*;
import java.util.*;

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

    public void run() {
        List<String> log = new ArrayList<>();
        String botAnswer;
        String botName = "Бот";
        String userName = "Вася";

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print(userName + " : ");
        String im = scanner.nextLine();
        String imAsk = STOP.equals(im) ? STOP : OUT.equals(im) ? OUT : CONTINUE;

        List<String> readPhrase = readPhrases();

        while (!OUT.equals(imAsk)) {
            if (CONTINUE.equals(imAsk)) {
                log.add(userName + " : " + im);
                int randomAnswer = random.nextInt(readPhrase.size() - 1);
               botAnswer = botName + " : " + readPhrase.get(randomAnswer);
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            while (STOP.equals(imAsk)) {
                log.add(userName + " : " + im);
                System.out.print(userName + " : ");
                im = scanner.nextLine();
                if (CONTINUE.equals(im)) {
                    log.add(userName + " : " + im);
                    botAnswer = botName + " : " + "продолжим";
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                    imAsk = CONTINUE;
                } else if (OUT.equals(im)) {
                    imAsk = OUT;
                }
            }
            if (OUT.equals(im)) {
                break;
            }
            System.out.print(userName + " : ");
            im = scanner.nextLine();
            imAsk = STOP.equals(im) ? STOP : OUT.equals(im) ? OUT : CONTINUE;
        }
        log.add(userName + " : " + im);
        System.out.println(botName + " : " + "До свидания");
        botAnswer = botName + " : До свидания";
        log.add(botAnswer);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> read = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(read::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = ".\\src\\main\\java\\ru\\job4j\\io\\consolechat\\log.txt";
        String botAnswers = ".\\src\\main\\java\\ru\\job4j\\io\\consolechat\\botAnswers.txt";

        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.run();
    }
}
