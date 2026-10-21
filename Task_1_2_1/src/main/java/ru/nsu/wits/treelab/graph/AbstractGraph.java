package ru.nsu.wits.treelab.graph;

import java.util.List;
import java.util.Objects;

/** Common public behavior; subclasses implement only their storage operations. */
public abstract class AbstractGraph implements Graph {

    @Override
    public final boolean addEdge(Vertex from, Vertex to) {
        checkVertexExists(from);
        checkVertexExists(to);
        if (hasEdge(from, to)) {
            return false;
        }
        storageAddEdge(from, to);
        return true;
    }

    @Override
    public final boolean removeEdge(Vertex from, Vertex to) {
        checkVertexExists(from);
        checkVertexExists(to);
        if (!hasEdge(from, to)) {
            return false;
        }
        storageRemoveEdge(from, to);
        return true;
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
        List<Vertex> thisVertices = getVertices();
        List<Vertex> otherVertices = other.getVertices();

        if (thisVertices.size() != otherVertices.size() || !thisVertices.containsAll(otherVertices)) {
            return false;
        }
        for (Vertex vertex : thisVertices) {
            List<Vertex> neighbors = getNeighbors(vertex);
            List<Vertex> otherNeighbors = other.getNeighbors(vertex);
            if (neighbors.size() != otherNeighbors.size() || !neighbors.containsAll(otherNeighbors)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int hash = 0;
        for (Vertex vertex : getVertices()) {
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
        for (Vertex vertex : getVertices()) {
            result.append(vertex).append(" -> ").append(getNeighbors(vertex))
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    // Абстрактные методы, которые должны реализовать наследники
    protected abstract void storageAddEdge(Vertex from, Vertex to);

    protected abstract void storageRemoveEdge(Vertex from, Vertex to);

    // Приватный хелпер для проверки вершин
    private void checkVertexExists(Vertex vertex) {
        Objects.requireNonNull(vertex, "vertex");
        if (!getVertices().contains(vertex)) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }
    }
}