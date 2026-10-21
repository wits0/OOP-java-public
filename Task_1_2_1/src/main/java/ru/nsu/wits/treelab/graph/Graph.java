package ru.nsu.wits.treelab.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import ru.nsu.wits.treelab.iostuff.GraphFileLoader;

public interface Graph {
    boolean addVertex(Vertex vertex);

    boolean removeVertex(Vertex vertex);

    boolean addEdge(Vertex from, Vertex to);

    boolean removeEdge(Vertex from, Vertex to);

    boolean hasEdge(Vertex from, Vertex to);

    List<Vertex> getVertices();

    List<Vertex> getNeighbors(Vertex vertex);

    default void readFromFile(Path path) throws IOException {
        GraphFileLoader.load(this, path);
    }
}