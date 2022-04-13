package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    static int lengthPathSource;

    public void packFiles(List<File> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {

            for (File file : sources) {
                if (file.getParentFile().toString().equals(file.toString().substring(0, lengthPathSource))) {
                    if (file.isDirectory()) {
                        zip.putNextEntry(new ZipEntry(file.getName() + "\\"));
                        continue;
                    }
                    zip.putNextEntry(new ZipEntry(file.getName()));
                    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(in.readAllBytes());
                    }
                } else if (file.isDirectory()) {
                    zip.putNextEntry(new ZipEntry(file.getParentFile().getName() + "\\" + file.getName() + "\\"));
                } else {
                    zip.putNextEntry(new ZipEntry(file.getParent().substring(lengthPathSource + 1)
                            + "\\" + file.getName()));
                    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(in.readAllBytes());
                    }
                }
            }
        }
    }

    public void packSingleFile(File source, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        }
    }

    public static void validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not all arguments are present");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);

        String pathSource = ArgsName.of(new String[]{args[0]}).get("d");
        if (!new File(pathSource).exists()) {
            throw new IllegalArgumentException("Wrong argument:" + args[0] + ". Directory: does not exist");
        }

        String excludeFile = ArgsName.of(new String[]{args[1]}).get("e");
        if (!excludeFile.startsWith(".")) {
            throw new IllegalArgumentException("Wrong argument: " + args[1] + ". The file extension must start with \".\"");
        }

        String pathTarget = ArgsName.of(new String[]{args[2]}).get("o");
        Pattern pattern = Pattern.compile("\\..+$");
        Matcher matcher = pattern.matcher(pathTarget);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong argument: " + args[2] + ". The file extension must start with \".\"");
        }

        Predicate<Path> predicateExcludeFile = p -> !p.toFile().getName().endsWith(excludeFile);
        List<File> filesToArchive = Search.search(Path.of(pathSource), predicateExcludeFile)
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());

        lengthPathSource = pathSource.length();

        Zip zip = new Zip();
        zip.packFiles(filesToArchive, new File(pathTarget));

        Zip zip1 = new Zip();
        zip1.packSingleFile(new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
