package ru.nsu.wits.treelab.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;
import ru.nsu.wits.treelab.operation.KahnTopologicalSorter;
import ru.nsu.wits.treelab.representation.AdjacencyListGraph;

class KahnTopologicalSorterTest {
    private final KahnTopologicalSorter sorter = new KahnTopologicalSorter();

    @Test
    void sortsDagWithMultiplePathsAndAnIsolatedVertexWithoutChangingIt() {
        Graph graph = new AdjacencyListGraph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex isolated = new Vertex("isolated");
        for (Vertex vertex : List.of(d, c, b, a, isolated)) {
            graph.addVertex(vertex);
        }
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, d);
        graph.addEdge(c, d);
        String before = graph.toString();

        List<Vertex> order = sorter.sort(graph);

        assertEquals(5, order.size());
        assertEquals(new HashSet<>(graph.getVertices()), new HashSet<>(order));
        for (Vertex from : graph.getVertices()) {
            for (Vertex to : graph.getNeighbors(from)) {
                assertTrue(order.indexOf(from) < order.indexOf(to));
            }
        }
        assertEquals(before, graph.toString());
    }

    @Test
    void rejectsCycle() {
        Graph graph = new AdjacencyListGraph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);
        graph.addEdge(b, a);

        assertThrows(IllegalArgumentException.class, () -> sorter.sort(graph));
    }

    @Test
    void handlesEmptyGraphAndRejectsNull() {
        assertTrue(sorter.sort(new AdjacencyListGraph()).isEmpty());
        assertThrows(NullPointerException.class, () -> sorter.sort(null));
    }
}
