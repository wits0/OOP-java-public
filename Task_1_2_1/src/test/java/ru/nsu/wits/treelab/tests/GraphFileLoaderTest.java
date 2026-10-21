package ru.nsu.wits.treelab.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;
import ru.nsu.wits.treelab.iostuff.GraphFileLoader;
import ru.nsu.wits.treelab.representation.AdjacencyListGraph;

class GraphFileLoaderTest {
    @TempDir
    Path directory;

    @Test
    void readsUtf8FileWithCommentsAndBlankLines() throws IOException {
        Path file = directory.resolve("graph.txt");
        Files.writeString(file, "# Example\n\nVERTICES 3\nА\nБ\nВ\n"
                + "\n# Directed edges\nEDGES 2\nА Б\nБ В\n");
        Graph graph = new AdjacencyListGraph();

        graph.readFromFile(file);

        Vertex a = new Vertex("А");
        Vertex b = new Vertex("Б");
        Vertex c = new Vertex("В");
        assertEquals(List.of(a, b, c), graph.getVertices());
        assertEquals(List.of(b), graph.getNeighbors(a));
        assertEquals(List.of(c), graph.getNeighbors(b));
        assertTrue(graph.getNeighbors(c).isEmpty());
    }

    @Test
    void loadsEmptyGraphAndRejectsLoadingIntoNonemptyGraph() throws IOException {
        Graph graph = new AdjacencyListGraph();
        GraphFileLoader.load(graph, stream("VERTICES 0\nEDGES 0\n"));
        assertTrue(graph.getVertices().isEmpty());

        graph.addVertex(new Vertex("A"));
        assertThrows(IllegalArgumentException.class,
                () -> GraphFileLoader.load(graph, stream("VERTICES 0\nEDGES 0\n")));
        assertEquals(List.of(new Vertex("A")), graph.getVertices());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "VERTICES -1\nEDGES 0\n",
        "NODES 0\nEDGES 0\n",
        "VERTICES 2\nA\nA\nEDGES 0\n",
        "VERTICES 1\nA\nEDGES 1\nA B\n",
        "VERTICES 2\nA\nB\nEDGES 2\nA B\nA B\n",
        "VERTICES 1\nA\nEDGES 1\n"
    })
    void rejectsMalformedInputWithoutPartiallyFillingGraph(String text) {
        Graph graph = new AdjacencyListGraph();

        assertThrows(IOException.class, () -> GraphFileLoader.load(graph, stream(text)));
        assertTrue(graph.getVertices().isEmpty());
    }

    private static ByteArrayInputStream stream(String text) {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }
}
