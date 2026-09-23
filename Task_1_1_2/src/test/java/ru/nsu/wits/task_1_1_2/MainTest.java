package ru.nsu.wits.task_1_1_2;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void mainStartsGameAndEventuallyStopsWhenInputEnds() {
        InputStream original = System.in;
        try {
            System.setIn(new ByteArrayInputStream(new byte[0]));
            assertThrows(NoSuchElementException.class, () -> Main.main(new String[0]));
        } finally {
            System.setIn(original);
        }
    }
}