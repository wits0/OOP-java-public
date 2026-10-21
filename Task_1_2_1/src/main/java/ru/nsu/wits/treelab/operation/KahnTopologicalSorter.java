package ru.nsu.wits.treelab.operation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;

/** Kahn's algorithm; a cycle causes IllegalArgumentException. */
public final class KahnTopologicalSorter implements TopologicalSorter {
    @Override
    public List<Vertex> sort(Graph graph) {
        Objects.requireNonNull(graph, "graph");

        List<Vertex> vertices = graph.getVertices();
        List<Integer> inDegrees = new ArrayList<>();
        List<Vertex> result = new ArrayList<>();
        Queue<Vertex> queue = new ArrayDeque<>();

        // Инициализация степеней нулями
        for (int i = 0; i < vertices.size(); i++) {
            inDegrees.add(0);
        }

        // Подсчет входящих степеней
        for (Vertex vertex : vertices) {
            for (Vertex neighbor : graph.getNeighbors(vertex)) {
                int index = vertices.indexOf(neighbor);
                inDegrees.set(index, inDegrees.get(index) + 1);
            }
        }

        // Добавляем в очередь вершины с нулевой степенью
        for (int i = 0; i < vertices.size(); i++) {
            if (inDegrees.get(i) == 0) {
                queue.add(vertices.get(i));
            }
        }

        // Основной цикл
        while (!queue.isEmpty()) {
            Vertex current = queue.poll(); // Извлекаем из начала очереди
            result.add(current);

            for (Vertex neighbor : graph.getNeighbors(current)) {
                int index = vertices.indexOf(neighbor);
                int degree = inDegrees.get(index) - 1;
                inDegrees.set(index, degree);

                if (degree == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Topological sort is impossible: graph has a cycle");
        }
        return result;
    }
}