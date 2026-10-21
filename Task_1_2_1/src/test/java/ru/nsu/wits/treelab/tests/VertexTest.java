package ru.nsu.wits.treelab.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.nsu.wits.treelab.graph.Vertex;

class VertexTest {
    @Test
    void verticesAreIdentifiedByName() {
        Vertex vertex = new Vertex("A");
        Vertex same = new Vertex("A");

        assertEquals("A", vertex.getName());
        assertEquals("A", vertex.toString());
        assertEquals(vertex, vertex);
        assertEquals(vertex, same);
        assertEquals(vertex.hashCode(), same.hashCode());
        assertNotEquals(vertex, new Vertex("B"));
        assertNotEquals(vertex, "A");
        assertNotEquals(vertex, null);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "A B"})
    void rejectsInvalidNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Vertex(name));
    }
}
