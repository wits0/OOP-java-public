package ru.nsu.wits.treelab.operation;

import java.util.ArrayList;
import java.util.Objects;
import ru.nsu.wits.treelab.interfacegraph.Graph;
import ru.nsu.wits.treelab.interfacegraph.Vertex;

/** Kahn's algorithm; a cycle causes IllegalArgumentException. */
public final class KahnTopologicalSorter implements TopologicalSorter {
    @Override
    public ArrayList<Vertex> sort(Graph graph) {
        Objects.requireNonNull(graph, "graph");
        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Integer> degrees = new ArrayList<>();
        ArrayList<Vertex> queue = new ArrayList<>();
        ArrayList<Vertex> order = new ArrayList<>();
        for (Vertex vertex : vertices) {
            degrees.add(0);
        }
        for (Vertex vertex : vertices) {
            for (Vertex neighbor : graph.getNeighbors(vertex)) {
                int index = vertices.indexOf(neighbor);
                degrees.set(index, degrees.get(index) + 1);
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            if (degrees.get(i) == 0) {
                queue.add(vertices.get(i));
            }
        }
        for (int head = 0; head < queue.size(); head++) {
            Vertex current = queue.get(head);
            order.add(current);
            for (Vertex neighbor : graph.getNeighbors(current)) {
                int index = vertices.indexOf(neighbor);
                int degree = degrees.get(index) - 1;
                degrees.set(index, degree);
                if (degree == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (order.size() != vertices.size()) {
            throw new IllegalArgumentException("Topological sort is impossible: graph has a cycle");
        }
        return order;
    }
}
