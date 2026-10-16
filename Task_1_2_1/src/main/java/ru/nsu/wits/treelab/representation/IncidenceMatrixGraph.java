package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import ru.nsu.wits.treelab.interfacegraph.AbstractGraph;

/** Each column is one edge: -1 at source, +1 at target, 2 for a loop. */
public final class IncidenceMatrixGraph extends AbstractGraph {
    private final ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    @Override
    protected void storageAddVertex() {
        ArrayList<Integer> row = new ArrayList<>();
        int edgeCount = matrix.isEmpty() ? 0 : matrix.get(0).size();
        for (int i = 0; i < edgeCount; i++) {
            row.add(0);
        }
        matrix.add(row);
    }

    @Override
    protected void storageRemoveVertex(int index) {
        for (int column = matrix.get(index).size() - 1; column >= 0; column--) {
            if (matrix.get(index).get(column) != 0) {
                removeColumn(column);
            }
        }
        matrix.remove(index);
    }

    @Override
    protected void storageAddEdge(int source, int target) {
        for (int row = 0; row < matrix.size(); row++) {
            int value = row == source ? -1 : row == target ? 1 : 0;
            matrix.get(row).add(source == target && row == source ? 2 : value);
        }
    }

    @Override
    protected void storageRemoveEdge(int source, int target) {
        removeColumn(findColumn(source, target));
    }

    @Override
    protected boolean storageHasEdge(int source, int target) {
        return findColumn(source, target) >= 0;
    }

    private int findColumn(int source, int target) {
        for (int column = 0; column < matrix.get(source).size(); column++) {
            if (source == target && matrix.get(source).get(column) == 2) {
                return column;
            }
            if (source != target && matrix.get(source).get(column) == -1
                    && matrix.get(target).get(column) == 1) {
                return column;
            }
        }
        return -1;
    }

    private void removeColumn(int column) {
        for (ArrayList<Integer> row : matrix) {
            row.remove(column);
        }
    }
}
