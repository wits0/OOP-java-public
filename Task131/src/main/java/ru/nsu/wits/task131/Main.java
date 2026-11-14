package ru.nsu.wits.task131;

import ru.nsu.wits.task131.finder.SubstringFinder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String DEFAULT_RESOURCE = "/input.txt";

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            Path filePath = Paths.get(args[0]);
            System.out.println(find(filePath, args[1]));
            return;
        }

        String needle = "бра";
        try (InputStream input = Main.class.getResourceAsStream(DEFAULT_RESOURCE)) {
            if (input == null) {
                throw new IllegalStateException("Ресурс '" + DEFAULT_RESOURCE + "' не найден");
            }

            System.out.println("Path file: " + DEFAULT_RESOURCE);
            System.out.println("What looking for:   \"" + needle + "\"");
            System.out.println("Output:  " + SubstringFinder.find(input, needle));
        }
    }

    public static List<Long> find(Path filePath, String needle) throws IOException {
        return SubstringFinder.find(filePath, needle);
    }
}
