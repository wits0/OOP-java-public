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
    /**
     * Выполняет топологическую сортировку вершин ориентированного ациклического графа
     * с использованием алгоритма Кана.
     *
     * @param graph граф, который необходимо отсортировать
     * @return список вершин в топологическом порядке
     * @throws IllegalArgumentException если граф содержит цикл (сортировка невозможна)
     */
    @Override
    public List<Vertex> sort(Graph graph) {
        Objects.requireNonNull(graph, "graph");

        List<Vertex> vertices = graph.getVertices();
        List<Integer> incomingEdgesCount = new ArrayList<>();
        Queue<Vertex> verticesWithZeroIncomingEdges = new ArrayDeque<>();

        // Инициализация степеней нулями
        for (int i = 0; i < vertices.size(); i++) {
            incomingEdgesCount.add(0);
        }

        // Подсчет входящих степеней
        for (Vertex vertex : vertices) {
            for (Vertex neighbor : graph.getNeighbors(vertex)) {
                int index = vertices.indexOf(neighbor);
                incomingEdgesCount.set(index, incomingEdgesCount.get(index) + 1);
            }
        }

        // Добавляем в очередь вершины с нулевой степенью
        for (int i = 0; i < vertices.size(); i++) {
            if (incomingEdgesCount.get(i) == 0) {
                verticesWithZeroIncomingEdges.add(vertices.get(i));
            }
        }
        List<Vertex> result = new ArrayList<>();
        // Основной цикл
        while (!verticesWithZeroIncomingEdges.isEmpty()) {
            Vertex current = verticesWithZeroIncomingEdges.poll(); // Извлекаем из начала очереди
            result.add(current);

            for (Vertex neighbor : graph.getNeighbors(current)) {
                int index = vertices.indexOf(neighbor);
                int degree = incomingEdgesCount.get(index) - 1;
                incomingEdgesCount.set(index, degree);

                if (degree == 0) {
                    verticesWithZeroIncomingEdges.add(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Topological sort is impossible: graph has a cycle");
        }
        return result;
    }
}