package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.wits.treelab.graph.AbstractGraph;
import ru.nsu.wits.treelab.graph.Vertex;

/** Each column is one edge: -1 at source, +1 at target, 2 for a loop. */
public final class IncidenceMatrixGraph extends AbstractGraph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<List<Integer>> matrix = new ArrayList<>();

    @Override
    public boolean addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);

        // Узнаем текущее количество ребер (столбцов)
        int edgeCount = matrix.isEmpty() ? 0 : matrix.get(0).size();

        // Создаем новую строку нулей (для новой вершины)
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            newRow.add(0);
        }
        matrix.add(newRow);
        return true;
    }

    @Override
    public boolean removeVertex(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index < 0) {
            return false;
        }

        // Удаляем все ребра (столбцы), связанные с этой вершиной
        for (int column = matrix.get(index).size() - 1; column >= 0; column--) {
            if (matrix.get(index).get(column) != 0) {
                removeColumn(column);
            }
        }

        vertices.remove(index);
        matrix.remove(index);
        return true;
    }

    @Override
    public boolean hasEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        if (source < 0 || target < 0) {
            return false;
        }
        return findColumn(source, target) >= 0;
    }

    @Override
    protected void storageAddEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);

        for (int row = 0; row < matrix.size(); row++) {
            int value = 0;

            if (source == target && row == source) {
                value = 2; // Петля
            } else if (row == source) {
                value = -1; // Начало ребра
            } else if (row == target) {
                value = 1; // Конец ребра
            }

            matrix.get(row).add(value);
        }
    }

    @Override
    protected void storageRemoveEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        int column = findColumn(source, target);
        if (column >= 0) {
            removeColumn(column);
        }
    }

    @Override
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public List<Vertex> getNeighbors(Vertex vertex) {
        int source = vertices.indexOf(vertex);
        if (source < 0) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }

        List<Vertex> neighbors = new ArrayList<>();
        // Проходим по всем столбцам (ребрам)
        for (int column = 0; column < matrix.get(source).size(); column++) {
            // Если в строке нашей вершины -1, значит ребро исходит из неё
            if (matrix.get(source).get(column) == -1) {
                // Ищем, где в этом столбце стоит 1 (или 2 для петли) — это конец ребра
                for (int row = 0; row < matrix.size(); row++) {
                    int val = matrix.get(row).get(column);
                    if (val == 1 || (val == 2 && row == source)) {
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
            if (source == target && matrix.get(source).get(column) == 2) {
                return column;
            }
            if (source != target
                    && matrix.get(source).get(column) == -1
                    && matrix.get(target).get(column) == 1) {
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
