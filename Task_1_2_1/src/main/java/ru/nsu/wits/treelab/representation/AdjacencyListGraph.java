package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.wits.treelab.graph.AbstractGraph;
import ru.nsu.wits.treelab.graph.Vertex;

/** Each row contains the indices of outgoing neighbors. */
public final class AdjacencyListGraph extends AbstractGraph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<List<Integer>> lists = new ArrayList<>();

    @Override
    public boolean addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        lists.add(new ArrayList<>());
        return true;
    }

    @Override
    public boolean removeVertex(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index < 0) {
            return false;
        }

        vertices.remove(index);
        lists.remove(index);

        for (List<Integer> neighbors : lists) {
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

    @Override
    public boolean hasEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        if (source < 0 || target < 0) {
            return false;
        }
        return lists.get(source).contains(target);
    }

    @Override
    protected void storageAddEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        lists.get(source).add(target);
    }

    @Override
    protected void storageRemoveEdge(Vertex from, Vertex to) {
        int source = vertices.indexOf(from);
        int target = vertices.indexOf(to);
        lists.get(source).remove(Integer.valueOf(target));
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
        for (int target : lists.get(source)) {
            neighbors.add(vertices.get(target));
        }
        return neighbors;
    }
}