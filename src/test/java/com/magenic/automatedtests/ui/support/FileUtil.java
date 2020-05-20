package com.magenic.automatedtests.ui.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtil {
    private static final String ROOT = "./src/test/java";

    public static String readFile(String fileName) throws IOException {
        return readFile(ROOT, fileName);
    }

    /**
     * Attempts to read the file recursively from the root directory provided.
     * @param root The root directory to start finding the file
     * @param fileName The name of the file to find
     * @return The found file as a String
     * @throws IOException If the file does not exist at the root provided
     */
    public static String readFile(String root, String fileName) throws IOException {
        StringBuffer buffer = new StringBuffer();
        String line;

        Optional<Path> filePath = findFile(root, fileName);

        try (BufferedReader br = new BufferedReader(
                new FileReader(
                        filePath.orElseThrow(FileNotFoundException::new)
                                .toString()))) {

            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }

        return buffer.toString();
    }

    /**
     * Finds the first instance of the file from the root directory
     * @param root The root directory to start searching
     * @param fileName The name of the file to search for
     * @return Optional value if the file was found or not
     * @throws IOException If no matching files were found
     */
    public static Optional<Path> findFile(String root, String fileName) throws IOException {
        return Files.find(
                Paths.get(root),
                100,
                (path, basicFileAttributes) -> {
                    File file = path.toFile();
                    return file.getName().equalsIgnoreCase(fileName);
                })
                .findFirst();
    }
}
