package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.wits.treelab.graph.AbstractGraph;
import ru.nsu.wits.treelab.graph.Vertex;

/**
 * Реализация ориентированного графа на основе матрицы смежности.
 * Направленное ребро хранится в ячейке matrix[source][target].
 */
public final class AdjacencyMatrixGraph extends AbstractGraph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<List<Boolean>> matrix = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }

        // 1. Добавляем вершину в общий список
        vertices.add(vertex);

        // 2. Добавляем новый столбец (false) во все существующие строки
        for (List<Boolean> row : matrix) {
            row.add(false);
        }

        // 3. Создаем новую строку, заполненную false (размер = количеству вершин)
        List<Boolean> newRow = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            newRow.add(false);
        }
        matrix.add(newRow);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeVertex(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index < 0) {
            return false;
        }

        // 1. Удаляем из списка вершин
        vertices.remove(index);

        // 2. Удаляем строку из матрицы
        matrix.remove(index);

        // 3. Удаляем столбец из всех оставшихся строк
        for (List<Boolean> row : matrix) {
            row.remove(index);
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        // Если вершины нет в графе, то и ребра нет
        if (source < 0 || target < 0) {
            return false;
        }
        return matrix.get(source).get(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageAddEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        matrix.get(source).set(target, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageRemoveEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        matrix.get(source).set(target, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertices() {
        // Возвращаем копию, чтобы внешний код не мог испортить внутреннее состояние
        return new ArrayList<>(vertices);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getNeighbors(Vertex vertex) {
        int source = vertices.indexOf(vertex);
        if (source < 0) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }

        List<Vertex> neighbors = new ArrayList<>();
        for (int target = 0; target < vertices.size(); target++) {
            if (matrix.get(source).get(target)) {
                neighbors.add(vertices.get(target));
            }
        }
        return neighbors;
    }
}