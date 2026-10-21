package ru.nsu.wits.treelab.iostuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import ru.nsu.wits.treelab.graph.Graph;

/**
 * Загружает граф из файла или входного потока в формате UTF-8.
 * Делегирует непосредственный парсинг классу {@link GraphParser}.
 */
public final class GraphFileLoader {

    private GraphFileLoader() {
    }

    /**
     * Загружает граф из файла.
     *
     * @param graph граф для заполнения.
     * @param path  путь к файлу.
     * @throws IOException если произошла ошибка чтения файла.
     */
    public static void load(Graph graph, Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            GraphParser.parse(graph, reader);
        }
    }

    /**
     * Загружает граф из входного потока.
     *
     * @param graph  граф для заполнения.
     * @param stream поток ввода.
     * @throws IOException если произошла ошибка чтения потока.
     * @throws NullPointerException если {@code stream} равен {@code null}.
     */
    public static void load(Graph graph, InputStream stream) throws IOException {
        Objects.requireNonNull(stream, "stream");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            GraphParser.parse(graph, reader);
        }
    }
}