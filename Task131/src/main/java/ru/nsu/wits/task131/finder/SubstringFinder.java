package ru.nsu.wits.task131.finder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Потоковый поиск всех вхождений подстроки в файле с помощью скользящего окна.
 *Игорь дарова!!!!Игорь Горин ПРИВЕТ!!!!
 * Текст читается как UTF-8 и обрабатывается по Unicode Code Points. Окно хранит
 * последние символы текста и имеет размер, равный количеству символов в шаблоне.
 * После чтения каждого символа содержимое окна сравнивается с искомой подстрокой.
 *
 * Позиции найденных вхождений возвращаются в Unicode Code Points от начала текста,
 * а не в байтах UTF-8 или индексах UTF-16.
 */
public final class SubstringFinder {

    private final String searchPattern;
    private final int searchPatternCodePointLength;
    private final Deque<Integer> rollingWindow = new ArrayDeque<>();

    private long processedCodePointCount;

    private SubstringFinder(String searchPattern) {
        if (searchPattern == null) {
            throw new IllegalArgumentException("searchPattern must not be null");
        }
        if (searchPattern.isEmpty()) {
            throw new IllegalArgumentException("searchPattern must not be empty");
        }

        this.searchPattern = searchPattern;
        this.searchPatternCodePointLength = searchPattern.codePointCount(0, searchPattern.length());
    }

    /**
     * Основная точка входа для поиска в файле.
     *
     * @param filePath путь к файлу
     * @param searchPattern искомая подстрока
     * @return индексы (в Unicode Code Points) начала каждого вхождения
     */
    public static List<Long> find(Path filePath, String searchPattern) throws IOException {
        try (InputStream input = Files.newInputStream(filePath)) {
            return find(input, searchPattern);
        }
    }

    /**
     * Поиск в потоке, например в ресурсе classpath.
     * Переданный поток остаётся открытым: закрывать его должен вызывающий код.
     *
     * @param input поток с текстом в UTF-8
     * @param searchPattern искомая подстрока
     * @return индексы (в Unicode Code Points) начала каждого вхождения
     * @throws IOException если чтение потока завершилось ошибкой
     */
    public static List<Long> find(InputStream input, String searchPattern) throws IOException {
        SubstringFinder substringFinder = new SubstringFinder(searchPattern);
        return substringFinder.scanInputStream(input);
    }

    private List<Long> scanInputStream(InputStream input) throws IOException {
        List<Long> matchStartPositions = new ArrayList<>();
        Reader utf8Reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        int currentCodePoint;
        while ((currentCodePoint = readNextCodePoint(utf8Reader)) != -1) {
            processedCodePointCount++;
            addCodePointToWindow(currentCodePoint);

            if (rollingWindow.size() == searchPatternCodePointLength
                    && doesWindowMatchSearchPattern()) {
                long matchStartPosition = processedCodePointCount - searchPatternCodePointLength;
                matchStartPositions.add(matchStartPosition);
            }
        }

        return matchStartPositions;
    }

    private void addCodePointToWindow(int currentCodePoint) {
        rollingWindow.addLast(currentCodePoint);
        if (rollingWindow.size() > searchPatternCodePointLength) {
            rollingWindow.removeFirst();
        }
    }

    private boolean doesWindowMatchSearchPattern() {
        int searchPatternCharOffset = 0;

        for (int windowCodePoint : rollingWindow) {
            int expectedCodePoint = searchPattern.codePointAt(searchPatternCharOffset);
            if (windowCodePoint != expectedCodePoint) {
                return false;
            }
            searchPatternCharOffset += Character.charCount(expectedCodePoint);
        }

        return true;
    }

    private static int readNextCodePoint(Reader utf8Reader) throws IOException {
        int firstCharacter = utf8Reader.read();
        if (firstCharacter == -1) {
            return -1;
        }

        char firstChar = (char) firstCharacter;
        if (!Character.isHighSurrogate(firstChar)) {
            return firstCharacter;
        }

        int secondCharacter = utf8Reader.read();
        if (secondCharacter == -1 || !Character.isLowSurrogate((char) secondCharacter)) {
            throw new IOException("Invalid UTF-16 sequence decoded from UTF-8 input");
        }

        return Character.toCodePoint(firstChar, (char) secondCharacter);
    }
}
