package ru.nsu.wits.treelab.graph;

import java.util.Objects;

/** A vertex is identified by its non-blank name. */
public final class Vertex {
    private final String name;

    public Vertex(String name) {
        if (name == null || name.isBlank() || name.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("Vertex name must be one non-blank word");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
