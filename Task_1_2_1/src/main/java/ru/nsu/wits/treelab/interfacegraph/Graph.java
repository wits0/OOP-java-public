package ru.nsu.wits.treelab.interfacegraph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import ru.nsu.wits.treelab.iostuff.GraphFileLoader;

/** Directed graph without parallel edges. */
public interface Graph {
    boolean addVertex(Vertex vertex);

    boolean removeVertex(Vertex vertex);

    boolean addEdge(Vertex from, Vertex to);

    boolean removeEdge(Vertex from, Vertex to);

    boolean hasEdge(Vertex from, Vertex to);

    ArrayList<Vertex> getVertices();

    ArrayList<Vertex> getNeighbors(Vertex vertex);

    default void readFromFile(Path path) throws IOException {
        GraphFileLoader.load(this, path);
    }
}
