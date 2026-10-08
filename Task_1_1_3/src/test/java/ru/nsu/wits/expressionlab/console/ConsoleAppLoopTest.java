package ru.nsu.wits.expressionlab.console;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ConsoleAppLoopTest {

    @Test
    void constructorCreatesApp() {
        ConsoleInput input = new ConsoleInput(new Scanner(""));
        ConsoleAppLoop app = new ConsoleAppLoop(input);
        assertNotNull(app);
    }
}
