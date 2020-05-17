package com.magenic.automatedtests.ui.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtil {
    private static final String ROOT = "./src/test/java";

    public static String readFile(String fileName) throws IOException {
        return readFile(ROOT, fileName);
    }

    /**
     * Attempts to read the file recursively from the root directory provided.
     * @param root
     * @param fileName
     * @return
     * @throws IOException If the file does not exist at the root provided
     */
    public static String readFile(String root, String fileName) throws IOException {
        StringBuffer buffer = new StringBuffer();

        String line;
        Stream<Path> filePath = Files.find(
                Paths.get(root),
                100,
                (path, basicFileAttributes) -> {
                    File file = path.toFile();
                    return file.getName().equalsIgnoreCase(fileName);
        });

        try (BufferedReader br = new BufferedReader(
                new FileReader(
                        filePath.findFirst()
                                .orElseThrow(FileNotFoundException::new)
                                .toString()))) {

            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }

        return buffer.toString();
    }
}
