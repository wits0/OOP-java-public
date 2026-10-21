package ru.nsu.wits.treelab.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import ru.nsu.wits.treelab.iostuff.GraphFileLoader;

/**
 * Интерфейс ориентированного графа без кратных ребер.
 * Определяет базовые операции для работы с вершинами и ребрами.
 */
public interface Graph {

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex добавляемая вершина.
     * @return {@code true}, если вершина была добавлена, {@code false},
     *         если такая вершина уже существует.
     */
    boolean addVertex(Vertex vertex);

    /**
     * Удаляет вершину из графа. При удалении вершины удаляются все связанные с ней ребра.
     *
     * @param vertex удаляемая вершина.
     * @return {@code true}, если вершина была удалена, {@code false},
     *         если такой вершины нет в графе.
     */
    boolean removeVertex(Vertex vertex);

    /**
     * Добавляет ориентированное ребро между двумя вершинами.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     * @return {@code true}, если ребро было добавлено, {@code false},
     *         если такое ребро уже существует.
     */
    boolean addEdge(Vertex from, Vertex to);

    /**
     * Удаляет ориентированное ребро между двумя вершинами.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     * @return {@code true}, если ребро было удалено, {@code false},
     *         если такого ребра не существует.
     */
    boolean removeEdge(Vertex from, Vertex to);

    /**
     * Проверяет наличие ориентированного ребра между двумя вершинами.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     * @return {@code true}, если ребро существует, иначе {@code false}.
     */
    boolean hasEdge(Vertex from, Vertex to);

    /**
     * Возвращает список всех вершин графа.
     *
     * @return список вершин.
     */
    List<Vertex> getVertices();

    /**
     * Возвращает список всех соседей указанной вершины (вершин, в которые ведут ребра).
     *
     * @param vertex вершина, для которой нужно найти соседей.
     * @return список соседних вершин.
     */
    List<Vertex> getNeighbors(Vertex vertex);

    /**
     * Читает граф из файла заданного формата.
     *
     * @param path путь к файлу с данными графа.
     * @throws IOException если произошла ошибка ввода-вывода при чтении файла.
     */
    default void readFromFile(Path path) throws IOException {
        GraphFileLoader.load(this, path);
    }
}