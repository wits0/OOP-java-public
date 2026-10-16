package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import ru.nsu.wits.treelab.interfacegraph.AbstractGraph;

/** A directed edge is stored at matrix[source][target]. */
public final class AdjacencyMatrixGraph extends AbstractGraph {
    private final ArrayList<ArrayList<Boolean>> matrix = new ArrayList<>();

    @Override
    protected void storageAddVertex() {
        for (ArrayList<Boolean> row : matrix) {
            row.add(false);
        }
        ArrayList<Boolean> row = new ArrayList<>();
        for (int i = 0; i <= matrix.size(); i++) {
            row.add(false);
        }
        matrix.add(row);
    }

    @Override
    protected void storageRemoveVertex(int index) {
        matrix.remove(index);
        for (ArrayList<Boolean> row : matrix) {
            row.remove(index);
        }
    }

    @Override
    protected void storageAddEdge(int source, int target) {
        matrix.get(source).set(target, true);
    }

    @Override
    protected void storageRemoveEdge(int source, int target) {
        matrix.get(source).set(target, false);
    }

    @Override
    protected boolean storageHasEdge(int source, int target) {
        return matrix.get(source).get(target);
    }
}
