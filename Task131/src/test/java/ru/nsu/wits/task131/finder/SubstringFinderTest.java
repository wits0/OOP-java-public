package ru.nsu.wits.task131.finder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.wits.task131.Main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubstringFinderTest {

    @TempDir
    Path tempDir;

    @Test
    void findsAllOccurrencesInUtf8File() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "абракадабра", StandardCharsets.UTF_8);

        assertEquals(List.of(1L, 8L), SubstringFinder.find(file, "бра"));
    }

    @Test
    void findsOverlappingOccurrences() throws IOException {
        Path file = tempDir.resolve("overlapping.txt");
        Files.writeString(file, "aaaaa", StandardCharsets.UTF_8);

        assertEquals(List.of(0L, 1L, 2L, 3L), SubstringFinder.find(file, "aa"));
    }

    @Test
    void findsOverlappingOccurrencesOfMultibyteCharacters() throws IOException {
        Path file = tempDir.resolve("multibyte-overlapping.txt");
        Files.writeString(file, "яяя", StandardCharsets.UTF_8);

        assertEquals(List.of(0L, 1L), SubstringFinder.find(file, "яя"));
    }

    @Test
    void returnsEmptyListWhenThereAreNoMatches() throws IOException {
        Path file = tempDir.resolve("no-matches.txt");
        Files.writeString(file, "abcdef", StandardCharsets.UTF_8);

        assertEquals(List.of(), SubstringFinder.find(file, "xyz"));
    }

    @Test
    void countsUnicodeCodePointsRatherThanUtf8BytesOrUtf16Chars() throws IOException {
        Path file = tempDir.resolve("unicode.txt");
        Files.writeString(file, "é猫😀é猫😀", StandardCharsets.UTF_8);

        assertEquals(List.of(2L, 5L), SubstringFinder.find(file, "😀"));
    }

    @Test
    void doesNotMatchHalfOfASurrogatePair() throws IOException {
        Path file = tempDir.resolve("surrogate-pair.txt");
        Files.writeString(file, "A😀B", StandardCharsets.UTF_8);

        assertEquals(List.of(), SubstringFinder.find(file, "\uD83D"));
    }

    @Test
    void returnsEmptyListForEmptyInput() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "", StandardCharsets.UTF_8);

        assertEquals(List.of(), SubstringFinder.find(file, "text"));
    }

    @Test
    void rejectsEmptyNeedle() {
        assertThrows(IllegalArgumentException.class,
                () -> SubstringFinder.find(InputStream.nullInputStream(), ""));
    }

    @Test
    void rejectsNullNeedle() {
        assertThrows(IllegalArgumentException.class,
                () -> SubstringFinder.find(InputStream.nullInputStream(), null));
    }

    @Test
    void readsInputFromMainResources() throws IOException {
        try (InputStream input = Main.class.getResourceAsStream("/input.txt")) {
            assertNotNull(input);
            assertEquals(List.of(1L, 8L), SubstringFinder.find(input, "бра"));
        }
    }
}
