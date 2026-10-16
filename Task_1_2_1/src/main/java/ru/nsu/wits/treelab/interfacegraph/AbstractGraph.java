package ru.nsu.wits.treelab.interfacegraph;

import java.util.ArrayList;
import java.util.Objects;

/** Common public behavior; subclasses implement only their storage operations. */
public abstract class AbstractGraph implements Graph {
    private final ArrayList<Vertex> vertices = new ArrayList<>();

    @Override
    public final boolean addVertex(Vertex vertex) {
        Objects.requireNonNull(vertex, "vertex");
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        storageAddVertex();
        return true;
    }

    @Override
    public final boolean removeVertex(Vertex vertex) {
        int index = vertices.indexOf(Objects.requireNonNull(vertex, "vertex"));
        if (index < 0) {
            return false;
        }
        storageRemoveVertex(index);
        vertices.remove(index);
        return true;
    }

    @Override
    public final boolean addEdge(Vertex from, Vertex to) {
        int source = indexOf(from);
        int target = indexOf(to);
        if (storageHasEdge(source, target)) {
            return false;
        }
        storageAddEdge(source, target);
        return true;
    }

    @Override
    public final boolean removeEdge(Vertex from, Vertex to) {
        int source = indexOf(from);
        int target = indexOf(to);
        if (!storageHasEdge(source, target)) {
            return false;
        }
        storageRemoveEdge(source, target);
        return true;
    }

    @Override
    public final boolean hasEdge(Vertex from, Vertex to) {
        return storageHasEdge(indexOf(from), indexOf(to));
    }

    @Override
    public final ArrayList<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public final ArrayList<Vertex> getNeighbors(Vertex vertex) {
        int source = indexOf(vertex);
        ArrayList<Vertex> neighbors = new ArrayList<>();
        for (int target = 0; target < vertices.size(); target++) {
            if (storageHasEdge(source, target)) {
                neighbors.add(vertices.get(target));
            }
        }
        return neighbors;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Graph)) {
            return false;
        }
        Graph other = (Graph) object;
        ArrayList<Vertex> others = other.getVertices();
        if (vertices.size() != others.size() || !vertices.containsAll(others)) {
            return false;
        }
        for (Vertex vertex : vertices) {
            ArrayList<Vertex> neighbors = getNeighbors(vertex);
            ArrayList<Vertex> otherNeighbors = other.getNeighbors(vertex);
            if (neighbors.size() != otherNeighbors.size()
                    || !neighbors.containsAll(otherNeighbors)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int hash = 0;
        for (Vertex vertex : vertices) {
            hash += vertex.hashCode();
            for (Vertex neighbor : getNeighbors(vertex)) {
                hash += 31 * vertex.hashCode() + neighbor.hashCode();
            }
        }
        return hash;
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();
        for (Vertex vertex : vertices) {
            result.append(vertex).append(" -> ").append(getNeighbors(vertex))
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    protected abstract void storageAddVertex();

    protected abstract void storageRemoveVertex(int index);

    protected abstract void storageAddEdge(int source, int target);

    protected abstract void storageRemoveEdge(int source, int target);

    protected abstract boolean storageHasEdge(int source, int target);

    private int indexOf(Vertex vertex) {
        Objects.requireNonNull(vertex, "vertex");
        int index = vertices.indexOf(vertex);
        if (index < 0) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }
        return index;
    }
}
