package ru.nsu.wits.expressionlab.console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ConsoleInputTest {

    @Test
    void readLineReturnsTrimmedLine() {
        Scanner scanner = new Scanner("hello  \nworld\n");
        ConsoleInput input = new ConsoleInput(scanner);
        assertEquals("hello", input.readLine());
        assertEquals("world", input.readLine());
    }

    @Test
    void readIntReadsInteger() {
        Scanner scanner = new Scanner("42\n");
        ConsoleInput input = new ConsoleInput(scanner);
        assertEquals(42, input.readInt());
    }

    @Test
    void readIntSkipsInvalidInput() {
        Scanner scanner = new Scanner("abc\n7\n");
        ConsoleInput input = new ConsoleInput(scanner);
        assertEquals(7, input.readInt());
    }
}
