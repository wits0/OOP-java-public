package ru.nsu.wits.treelab.operation;

import java.util.List;
import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;

public interface TopologicalSorter {
    List<Vertex> sort(Graph graph);
}
