package ru.nsu.wits.treelab.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;
import ru.nsu.wits.treelab.representation.AdjacencyListGraph;
import ru.nsu.wits.treelab.representation.AdjacencyMatrixGraph;
import ru.nsu.wits.treelab.representation.IncidenceMatrixGraph;

class GraphTest {
    private static final Vertex A = new Vertex("A");
    private static final Vertex B = new Vertex("B");
    private static final Vertex C = new Vertex("C");
    private static final Vertex D = new Vertex("D");

    static Stream<Graph> graphs() {
        return Stream.of(new AdjacencyListGraph(), new AdjacencyMatrixGraph(),
                new IncidenceMatrixGraph());
    }

    @ParameterizedTest(name = "vertex operations, representation {index}")
    @MethodSource("graphs")
    void removesVertexWithItsEdgesAndPreservesOtherEdges(Graph graph) {
        for (Vertex vertex : List.of(A, B, C, D)) {
            assertTrue(graph.addVertex(vertex));
        }
        assertFalse(graph.addVertex(new Vertex("A")));
        graph.addEdge(A, B);
        graph.addEdge(B, C);
        graph.addEdge(C, A);
        graph.addEdge(A, D);
        graph.addEdge(D, C);

        assertTrue(graph.removeVertex(B));
        assertFalse(graph.removeVertex(B));
        assertEquals(List.of(A, C, D), graph.getVertices());
        assertEquals(List.of(D), graph.getNeighbors(A));
        assertEquals(List.of(A), graph.getNeighbors(C));
        assertEquals(List.of(C), graph.getNeighbors(D));
        assertFalse(graph.hasEdge(A, B));
        assertFalse(graph.hasEdge(B, C));

        assertTrue(graph.addVertex(B));
        assertTrue(graph.getNeighbors(B).isEmpty());
        assertTrue(graph.hasEdge(D, C));
    }

    @ParameterizedTest(name = "edge operations, representation {index}")
    @MethodSource("graphs")
    void addsAndRemovesDirectedEdgesAndReturnsCopies(Graph graph) {
        graph.addVertex(A);
        graph.addVertex(B);

        assertTrue(graph.addEdge(A, B));
        assertFalse(graph.addEdge(A, B));
        assertTrue(graph.hasEdge(A, B));
        assertFalse(graph.hasEdge(B, A));
        assertEquals(List.of(B), graph.getNeighbors(A));
        assertTrue(graph.getNeighbors(B).isEmpty());

        graph.getVertices().clear();
        graph.getNeighbors(A).clear();
        assertEquals(List.of(A, B), graph.getVertices());
        assertTrue(graph.hasEdge(A, B));

        assertTrue(graph.removeEdge(A, B));
        assertFalse(graph.removeEdge(A, B));
        assertFalse(graph.hasEdge(A, B));
        assertTrue(graph.getNeighbors(A).isEmpty());
    }

    @ParameterizedTest(name = "unknown vertices, representation {index}")
    @MethodSource("graphs")
    void rejectsEdgesAndNeighborQueriesForUnknownVertices(Graph graph) {
        graph.addVertex(A);

        assertFalse(graph.hasEdge(A, B));
        assertFalse(graph.hasEdge(B, A));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(A, B));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(B, A));
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(A, B));
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(B));
        assertThrows(NullPointerException.class, () -> graph.addEdge(null, A));
    }

    @Test
    void equalityAndHashCodeDependOnContentsRatherThanRepresentation() {
        Graph list = new AdjacencyListGraph();
        Graph matrix = new AdjacencyMatrixGraph();
        Graph incidence = new IncidenceMatrixGraph();
        for (Graph graph : List.of(list, matrix, incidence)) {
            graph.addVertex(A);
            graph.addVertex(B);
            graph.addEdge(A, B);
        }

        assertEquals(list, list);
        assertEquals(list, matrix);
        assertEquals(matrix, incidence);
        assertEquals(incidence, list);
        assertEquals(list.hashCode(), matrix.hashCode());
        assertEquals(matrix.hashCode(), incidence.hashCode());
        assertEquals("A -> [B]" + System.lineSeparator()
                + "B -> []" + System.lineSeparator(), list.toString());
        assertNotEquals(list, null);
        assertNotEquals(list, "graph");

        matrix.removeEdge(A, B);
        assertNotEquals(list, matrix);
        matrix.addEdge(B, A);
        assertNotEquals(list, matrix);
        matrix.removeVertex(B);
        assertNotEquals(list, matrix);
        matrix.addVertex(C);
        assertNotEquals(list, matrix);
    }
}
