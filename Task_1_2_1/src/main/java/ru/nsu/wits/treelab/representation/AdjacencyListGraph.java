package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.wits.treelab.graph.AbstractGraph;
import ru.nsu.wits.treelab.graph.Vertex;

/**
 * Реализация ориентированного графа на основе списка смежности.
 * Каждая строка внутреннего списка содержит индексы исходящих соседей.
 */
public final class AdjacencyListGraph extends AbstractGraph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<List<Integer>> adjacencyList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        adjacencyList.add(new ArrayList<>());
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

        vertices.remove(index);
        adjacencyList.remove(index);

        for (List<Integer> neighbors : adjacencyList) {
            neighbors.remove(Integer.valueOf(index));
            for (int i = 0; i < neighbors.size(); i++) {
                int target = neighbors.get(i);
                if (target > index) {
                    neighbors.set(i, target - 1);
                }
            }
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
        if (source < 0 || target < 0) {
            return false;
        }
        return adjacencyList.get(source).contains(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageAddEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        adjacencyList.get(source).add(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void storageRemoveEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        adjacencyList.get(source).remove(Integer.valueOf(target));
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
        for (int target : adjacencyList.get(source)) {
            neighbors.add(vertices.get(target));
        }
        return neighbors;
    }
}