package ru.nsu.wits.treelab.iostuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;
import ru.nsu.wits.treelab.interfacegraph.Graph;
import ru.nsu.wits.treelab.interfacegraph.Vertex;

/** Reads UTF-8 files: VERTICES count, names, EDGES count, source target pairs. */
public final class GraphFileLoader {
    private GraphFileLoader() {
    }

    public static void load(Graph graph, Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            load(graph, reader);
        }
    }

    public static void load(Graph graph, InputStream stream) throws IOException {
        Objects.requireNonNull(stream, "stream");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8));
        load(graph, reader);
    }

    private static void load(Graph graph, BufferedReader reader) throws IOException {
        Objects.requireNonNull(graph, "graph");
        if (!graph.getVertices().isEmpty()) {
            throw new IllegalArgumentException("Load into an empty graph");
        }
        int vertexCount = readCount(reader, "VERTICES");
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            ArrayList<String> tokens = tokens(requireLine(reader, "vertex"));
            if (tokens.size() != 1) {
                throw new IOException("Expected one vertex name");
            }
            Vertex vertex;
            try {
                vertex = new Vertex(tokens.get(0));
            } catch (IllegalArgumentException exception) {
                throw new IOException("Invalid vertex name", exception);
            }
            if (vertices.contains(vertex)) {
                throw new IOException("Duplicate vertex: " + vertex);
            }
            vertices.add(vertex);
        }
        int edgeCount = readCount(reader, "EDGES");
        ArrayList<Vertex> sources = new ArrayList<>();
        ArrayList<Vertex> targets = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            ArrayList<String> pair = tokens(requireLine(reader, "edge"));
            if (pair.size() != 2) {
                throw new IOException("Expected source and target names");
            }
            Vertex source;
            Vertex target;
            try {
                source = new Vertex(pair.get(0));
                target = new Vertex(pair.get(1));
            } catch (IllegalArgumentException exception) {
                throw new IOException("Invalid vertex name in edge: " + pair, exception);
            }
            if (!vertices.contains(source) || !vertices.contains(target)) {
                throw new IOException("Edge references unknown vertex: " + pair);
            }
            for (int j = 0; j < sources.size(); j++) {
                if (sources.get(j).equals(source) && targets.get(j).equals(target)) {
                    throw new IOException("Duplicate edge: " + source + " -> " + target);
                }
            }
            sources.add(source);
            targets.add(target);
        }
        if (nextLine(reader) != null) {
            throw new IOException("Unexpected data after the last edge");
        }
        for (Vertex vertex : vertices) {
            graph.addVertex(vertex);
        }
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), targets.get(i));
        }
    }

    private static int readCount(BufferedReader reader, String header) throws IOException {
        ArrayList<String> parts = tokens(requireLine(reader, header));
        if (parts.size() != 2 || !header.equals(parts.get(0))) {
            throw new IOException("Expected " + header + " followed by a count");
        }
        try {
            int count = Integer.parseInt(parts.get(1));
            if (count < 0) {
                throw new NumberFormatException("negative count");
            }
            return count;
        } catch (NumberFormatException exception) {
            throw new IOException("Invalid " + header + " count", exception);
        }
    }

    private static String requireLine(BufferedReader reader, String kind) throws IOException {
        String line = nextLine(reader);
        if (line == null) {
            throw new IOException("Unexpected end of file while reading " + kind);
        }
        return line;
    }

    private static String nextLine(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String content = line.strip();
            if (!content.isEmpty() && !content.startsWith("#")) {
                return content;
            }
        }
        return null;
    }

    private static ArrayList<String> tokens(String line) {
        ArrayList<String> parts = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            parts.add(tokenizer.nextToken());
        }
        return parts;
    }
}
