package ru.nsu.wits.treelab.representation;

import java.util.ArrayList;
import ru.nsu.wits.treelab.interfacegraph.AbstractGraph;

/** Each row contains the indices of outgoing neighbors. */
public final class AdjacencyListGraph extends AbstractGraph {
    private final ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

    @Override
    protected void storageAddVertex() {
        lists.add(new ArrayList<>());
    }

    @Override
    protected void storageRemoveVertex(int index) {
        lists.remove(index);
        for (ArrayList<Integer> neighbors : lists) {
            neighbors.remove(Integer.valueOf(index));
            for (int i = 0; i < neighbors.size(); i++) {
                int target = neighbors.get(i);
                if (target > index) {
                    neighbors.set(i, target - 1);
                }
            }
        }
    }

    @Override
    protected void storageAddEdge(int source, int target) {
        lists.get(source).add(target);
    }

    @Override
    protected void storageRemoveEdge(int source, int target) {
        lists.get(source).remove(Integer.valueOf(target));
    }

    @Override
    protected boolean storageHasEdge(int source, int target) {
        return lists.get(source).contains(target);
    }
}
