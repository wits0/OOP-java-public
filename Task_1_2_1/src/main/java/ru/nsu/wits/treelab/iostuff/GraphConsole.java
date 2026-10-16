package ru.nsu.wits.treelab.iostuff;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import ru.nsu.wits.treelab.interfacegraph.Graph;
import ru.nsu.wits.treelab.interfacegraph.Vertex;

/** Presentation of the sample run. */
public final class GraphConsole {
    private final PrintStream output;

    public GraphConsole(PrintStream output) {
        this.output = Objects.requireNonNull(output, "output");
    }

    public void show(String title, Graph graph, ArrayList<Vertex> order) {
        output.println(title);
        output.print(graph);
        output.println("Topological order: " + order);
        output.println();
    }

    public void showComparison(boolean equal) {
        output.println("All three representations are equal: " + equal);
    }
}
