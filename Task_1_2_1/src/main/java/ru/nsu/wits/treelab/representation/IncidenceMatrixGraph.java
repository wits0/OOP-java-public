package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.wits.treelab.graph.AbstractGraph;
import ru.nsu.wits.treelab.graph.Vertex;

/**
 * Реализация ориентированного графа на основе матрицы инцидентности.
 * Каждый столбец представляет одно ребро: -1 в строке истока, +1 в строке стока,
 * 2 для петли (вершина связана сама с собой).
 */
public final class IncidenceMatrixGraph extends AbstractGraph {
    /** Значение для начала ребра (исток). */
    private static final int EDGE_START = -1;
    /** Значение для конца ребра (сток). */
    private static final int EDGE_END = 1;
    /** Значение для петли. */
    private static final int LOOP = 2;

    private final List<Vertex> vertices = new ArrayList<>();
    private final List<List<Integer>> matrix = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);

        int edgeCount = matrix.isEmpty() ? 0 : matrix.get(0).size();
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            newRow.add(0);
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

        for (int column = matrix.get(index).size() - 1; column >= 0; column--) {
            if (matrix.get(index).get(column) != 0) {
                removeColumn(column);
            }
        }

        vertices.remove(index);
        matrix.remove(index);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        if (source < 0 || target < 0) {
            return false;
        }
        return findColumn(source, target) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageAddEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);

        for (int row = 0; row < matrix.size(); row++) {
            int value = 0;
            if (source == target && row == source) {
                value = LOOP;
            } else if (row == source) {
                value = EDGE_START;
            } else if (row == target) {
                value = EDGE_END;
            }
            matrix.get(row).add(value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageRemoveEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        int column = findColumn(source, target);
        if (column >= 0) {
            removeColumn(column);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertices() {
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
        for (int column = 0; column < matrix.get(source).size(); column++) {
            if (matrix.get(source).get(column) == EDGE_START) {
                for (int row = 0; row < matrix.size(); row++) {
                    int val = matrix.get(row).get(column);
                    if (val == EDGE_END || (val == LOOP && row == source)) {
                        neighbors.add(vertices.get(row));
                        break;
                    }
                }
            }
        }
        return neighbors;
    }

    private int findColumn(int source, int target) {
        for (int column = 0; column < matrix.get(source).size(); column++) {
            if (source == target && matrix.get(source).get(column) == LOOP) {
                return column;
            }
            if (source != target
                    && matrix.get(source).get(column) == EDGE_START
                    && matrix.get(target).get(column) == EDGE_END) {
                return column;
            }
        }
        return -1;
    }

    private void removeColumn(int column) {
        for (List<Integer> row : matrix) {
            row.remove(column);
        }
    }
}