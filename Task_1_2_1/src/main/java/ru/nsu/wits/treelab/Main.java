package ru.nsu.wits.treelab;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import ru.nsu.wits.treelab.interfacegraph.Graph;
import ru.nsu.wits.treelab.iostuff.GraphConsole;
import ru.nsu.wits.treelab.iostuff.GraphFileLoader;
import ru.nsu.wits.treelab.operation.KahnTopologicalSorter;
import ru.nsu.wits.treelab.operation.TopologicalSorter;
import ru.nsu.wits.treelab.representation.AdjacencyListGraph;
import ru.nsu.wits.treelab.representation.AdjacencyMatrixGraph;
import ru.nsu.wits.treelab.representation.IncidenceMatrixGraph;

/** Application entry point. Accepts an optional path to a graph file. */
public final class Main {
    private static final String DEFAULT_RESOURCE = "/graph.txt";

    private Main() {
    }

    public static void main(String[] args) {
        try {
            ArrayList<Graph> graphs = createGraphs();
            for (Graph graph : graphs) {
                loadGraph(graph, args);
            }
            displayResults(graphs);
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("Cannot process graph: " + exception.getMessage());
        }
    }

    private static ArrayList<Graph> createGraphs() {
        ArrayList<Graph> graphs = new ArrayList<>();
        graphs.add(new AdjacencyMatrixGraph());
        graphs.add(new IncidenceMatrixGraph());
        graphs.add(new AdjacencyListGraph());
        return graphs;
    }

    private static void loadGraph(Graph graph, String[] args) throws IOException {
        if (args.length > 0) {
            GraphFileLoader.load(graph, Path.of(args[0]));
            return;
        }
        try (InputStream input = Main.class.getResourceAsStream(DEFAULT_RESOURCE)) {
            if (input == null) {
                throw new IOException("Resource " + DEFAULT_RESOURCE
                        + " not found. Pass a path to a graph .txt file as an argument.");
            }
            GraphFileLoader.load(graph, input);
        }
    }

    private static void displayResults(ArrayList<Graph> graphs) {
        GraphConsole console = new GraphConsole(System.out);
        TopologicalSorter sorter = new KahnTopologicalSorter();
        for (Graph graph : graphs) {
            console.show(graph.getClass().getSimpleName(), graph, sorter.sort(graph));
        }
        boolean equal = graphs.get(0).equals(graphs.get(1))
                && graphs.get(1).equals(graphs.get(2));
        console.showComparison(equal);
    }
}
