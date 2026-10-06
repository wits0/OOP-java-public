package ru.nsu.wits.expressionlab;

import java.util.Scanner;

import ru.nsu.wits.expressionlab.console.ConsoleAppLoop;
import ru.nsu.wits.expressionlab.console.ConsoleInput;

/**
 * Entry point of the application.
 */
public class Main {

    /**
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);
        ConsoleAppLoop app = new ConsoleAppLoop(input);
        app.run();
    }
}