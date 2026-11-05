package ru.nsu.wits.treelab.graph;

import java.util.List;
import java.util.Objects;

/**
 * Базовый абстрактный класс для всех реализаций графа.
 * Содержит общую логику добавления/удаления ребер и переопределения
 * методов {@link Object}. Наследники реализуют только операции хранения.
 */
public abstract class AbstractGraph implements Graph {

    /**
     * Добавляет ориентированное ребро между двумя вершинами.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     * @return {@code true}, если ребро было добавлено, {@code false},
     *         если такое ребро уже существует.
     * @throws IllegalArgumentException если одна из вершин не существует в графе.
     */
    @Override
    public final boolean addEdge(Vertex from, Vertex to) {
        checkVertexExists(from);
        checkVertexExists(to);
        if (hasEdge(from, to)) {
            return false;
        }
        storageAddEdge(from, to);
        return true;
    }

    /**
     * Удаляет ориентированное ребро между двумя вершинами.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     * @return {@code true}, если ребро было удалено, {@code false},
     *         если такого ребра не существует.
     * @throws IllegalArgumentException если одна из вершин не существует в графе.
     */
    @Override
    public final boolean removeEdge(Vertex from, Vertex to) {
        checkVertexExists(from);
        checkVertexExists(to);
        if (!hasEdge(from, to)) {
            return false;
        }
        storageRemoveEdge(from, to);
        return true;
    }

    /**
     * Сравнивает текущий граф с другим объектом.
     * Графы считаются равными, если у них одинаковый набор вершин
     * и одинаковый набор ребер.
     *
     * @param object объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Graph)) {
            return false;
        }
        Graph other = (Graph) object;
        List<Vertex> thisVertices = getVertices();
        List<Vertex> otherVertices = other.getVertices();

        if (thisVertices.size() != otherVertices.size()
                || !thisVertices.containsAll(otherVertices)) {
            return false;
        }
        for (Vertex vertex : thisVertices) {
            List<Vertex> neighbors = getNeighbors(vertex);
            List<Vertex> otherNeighbors = other.getNeighbors(vertex);
            if (neighbors.size() != otherNeighbors.size()
                    || !neighbors.containsAll(otherNeighbors)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Возвращает хеш-код графа, основанный на хеш-кодах вершин и их связей.
     *
     * @return хеш-код графа.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        for (Vertex vertex : getVertices()) {
            hash += vertex.hashCode();
            for (Vertex neighbor : getNeighbors(vertex)) {
                hash += 31 * vertex.hashCode() + neighbor.hashCode();
            }
        }
        return hash;
    }

    /**
     * Возвращает строковое представление графа в виде списков смежности.
     *
     * @return строковое представление графа.
     */
    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();
        for (Vertex vertex : getVertices()) {
            result.append(vertex).append(" -> ").append(getNeighbors(vertex))
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * Внутренний метод для добавления ребра в конкретную структуру данных наследника.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     */
    protected abstract void storageAddEdge(Vertex from, Vertex to);

    /**
     * Внутренний метод для удаления ребра из конкретной структуры данных наследника.
     *
     * @param from вершина-исток.
     * @param to   вершина-сток.
     */
    protected abstract void storageRemoveEdge(Vertex from, Vertex to);

    /**
     * Проверяет, существует ли указанная вершина в графе.
     *
     * @param vertex вершина для проверки.
     * @throws IllegalArgumentException если вершина не найдена в графе.
     */
    private void checkVertexExists(Vertex vertex) {
        Objects.requireNonNull(vertex, "vertex");
        if (!getVertices().contains(vertex)) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }
    }
}