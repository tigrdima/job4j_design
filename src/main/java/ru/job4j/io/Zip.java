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

    public void packFiles(List<File> sources, File target, int sourceNameCount) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                int count = file.toPath().getNameCount();
                zip.putNextEntry(new ZipEntry(file.toPath().subpath(sourceNameCount, count) + file.getName()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(in.readAllBytes());
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

    private static String[] validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all arguments are present");
        }
        args[0] = ArgsName.of(new String[]{args[0]}).get("d");
        if (!new File(args[0]).exists()) {
            throw new IllegalArgumentException("Wrong argument:" + args[0] + ". Directory: does not exist");
        }
        args[1] = ArgsName.of(new String[]{args[1]}).get("e");
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong argument: " + args[1] + ". The file extension must start with \".\"");
        }
        args[2] = ArgsName.of(new String[]{args[2]}).get("o");
        Pattern pattern = Pattern.compile("\\..+$");
        Matcher matcher = pattern.matcher(args[2]);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong argument: " + args[2] + ". The file extension must start with \".\"");
        }
        return args;
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        String pathSource = args[0];
        String excludeFile = args[1];
        String pathTarget = args[2];

        Predicate<Path> predicateExcludeFile = p -> !p.toFile().getName().endsWith(excludeFile);
        List<File> filesToArchive = Search.search(Path.of(pathSource), predicateExcludeFile)
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());

        int sourceNameCount = Path.of(pathSource).getNameCount();

        Zip zip = new Zip();
        zip.packFiles(filesToArchive, new File(pathTarget), sourceNameCount);

        Zip zip1 = new Zip();
        zip1.packSingleFile(new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
