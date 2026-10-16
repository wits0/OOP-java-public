package ru.nsu.wits.treelab.operation;

import java.util.ArrayList;
import ru.nsu.wits.treelab.interfacegraph.Graph;
import ru.nsu.wits.treelab.interfacegraph.Vertex;

/** A strategy for ordering the vertices of a directed acyclic graph. */
public interface TopologicalSorter {
    ArrayList<Vertex> sort(Graph graph);
}
