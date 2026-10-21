package ru.nsu.wits.treelab.iostuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;

/** Parses graph data from a buffered reader. */
final class GraphParser {

    private GraphParser() {
    }

    static void parse(Graph graph, BufferedReader reader) throws IOException {
        if (!graph.getVertices().isEmpty()) {
            throw new IllegalArgumentException("Load into an empty graph");
        }

        List<Vertex> vertices = readVertices(reader);
        List<Vertex[]> edges = readEdges(reader, vertices);

        // Заполняем граф только после успешного парсинга
        for (Vertex vertex : vertices) {
            graph.addVertex(vertex);
        }
        for (Vertex[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
    }

    private static List<Vertex> readVertices(BufferedReader reader) throws IOException {
        int vertexCount = readCount(reader, "VERTICES");
        List<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < vertexCount; i++) {
            List<String> tokens = tokens(requireLine(reader, "vertex"));
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
        return vertices;
    }

    private static List<Vertex[]> readEdges(BufferedReader reader, List<Vertex> vertices)
            throws IOException {
        int edgeCount = readCount(reader, "EDGES");
        List<Vertex[]> edges = new ArrayList<>();

        for (int i = 0; i < edgeCount; i++) {
            List<String> pair = tokens(requireLine(reader, "edge"));
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

            for (Vertex[] existingEdge : edges) {
                if (existingEdge[0].equals(source) && existingEdge[1].equals(target)) {
                    throw new IOException("Duplicate edge: " + source + " -> " + target);
                }
            }
            edges.add(new Vertex[]{source, target});
        }

        if (nextLine(reader) != null) {
            throw new IOException("Unexpected data after the last edge");
        }
        return edges;
    }

    private static int readCount(BufferedReader reader, String header) throws IOException {
        List<String> parts = tokens(requireLine(reader, header));
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

    private static List<String> tokens(String line) {
        List<String> parts = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            parts.add(tokenizer.nextToken());
        }
        return parts;
    }
}