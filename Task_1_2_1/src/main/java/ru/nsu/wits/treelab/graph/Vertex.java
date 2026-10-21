package ru.nsu.wits.treelab.graph;

import java.util.Objects;

/**
 * Представляет собой вершину графа.
 * Вершина идентифицируется своим уникальным непустым именем.
 */
public final class Vertex {
    private final String name;

    /**
     * Создает новую вершину с заданным именем.
     *
     * @param name имя вершины. Должно состоять из одного непустого слова,
     *             не содержащего пробельных символов.
     * @throws IllegalArgumentException если имя пустое, состоит из пробелов
     *                                  или содержит пробельные символы.
     */
    public Vertex(String name) {
        if (name == null || name.isBlank() || name.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("Vertex name must be one non-blank word");
        }
        this.name = name;
    }

    /**
     * Возвращает имя вершины.
     *
     * @return строковое имя вершины.
     */
    public String getName() {
        return name;
    }

    /**
     * Сравнивает текущую вершину с другим объектом.
     * Две вершины считаются равными, если совпадают их имена.
     *
     * @param object объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Vertex)) {
            return false;
        }
        Vertex other = (Vertex) object;
        return name.equals(other.name);
    }

    /**
     * Возвращает хеш-код вершины, основанный на ее имени.
     *
     * @return хеш-код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Возвращает строковое представление вершины (ее имя).
     *
     * @return имя вершины.
     */
    @Override
    public String toString() {
        return name;
    }
}