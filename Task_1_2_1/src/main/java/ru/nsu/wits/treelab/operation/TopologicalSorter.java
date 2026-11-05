package ru.nsu.wits.treelab.operation;

import java.util.List;

import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;

/**
 * Стратегия для упорядочивания вершин ориентированного ациклического графа.
 * Реализации этого интерфейса предоставляют конкретные алгоритмы
 * топологической сортировки.
 */
public interface TopologicalSorter {

    /**
     * Выполняет топологическую сортировку вершин заданного графа.
     *
     * @param graph граф, который необходимо отсортировать.
     * @return список вершин в топологическом порядке.
     * @throws IllegalArgumentException если граф содержит цикл и топологическая
     *                                  сортировка невозможна.
     */
    List<Vertex> sort(Graph graph);
}