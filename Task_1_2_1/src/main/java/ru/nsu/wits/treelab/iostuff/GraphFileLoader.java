package ru.nsu.wits.treelab.iostuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import ru.nsu.wits.treelab.graph.Graph;

/** Reads UTF-8 files and delegates parsing to GraphParser. */
public final class GraphFileLoader {

    private GraphFileLoader() {
    }

    public static void load(Graph graph, Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            GraphParser.parse(graph, reader);
        }
    }

    public static void load(Graph graph, InputStream stream) throws IOException {
        Objects.requireNonNull(stream, "stream");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            GraphParser.parse(graph, reader);
        }
    }
}