package ru.nsu.wits.treelab.iostuff;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

import ru.nsu.wits.treelab.graph.Graph;
import ru.nsu.wits.treelab.graph.Vertex;

/**
 * Класс для вывода результатов работы программы в консоль.
 */
public final class GraphConsole {
    private final PrintStream output;

    /**
     * Создает консольный вывод с заданным потоком.
     *
     * @param output поток вывода.
     * @throws NullPointerException если {@code output} равен {@code null}.
     */
    public GraphConsole(PrintStream output) {
        this.output = Objects.requireNonNull(output, "output");
    }

    /**
     * Выводит название графа, его представление и топологический порядок.
     *
     * @param title заголовок (обычно имя класса графа).
     * @param graph объект графа.
     * @param order результат топологической сортировки.
     */
    public void show(String title, Graph graph, List<Vertex> order) {
        output.println(title);
        output.print(graph);
        output.println("Topological order: " + order);
        output.println();
    }

    /**
     * Выводит результат сравнения графов.
     *
     * @param equal {@code true}, если все графы равны, иначе {@code false}.
     */
    public void showComparison(boolean equal) {
        output.println("All three representations are equal: " + equal);
    }
}