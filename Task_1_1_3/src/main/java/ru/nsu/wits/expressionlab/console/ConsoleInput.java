package ru.nsu.wits.expressionlab.console;

import java.util.Scanner;

/**
 * Reads user input from the console.
 */
public class ConsoleInput {

    private final Scanner scanner;

    /**
     * @param scanner scanner to read input from
     */
    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads one line from the console.
     *
     * @return the line without trailing whitespace
     */
    public String readLine() {
        return scanner.nextLine().trim();
    }

    /**
     * Reads one integer from the console.
     * Repeats the request until a valid integer is entered.
     *
     * @return the entered integer
     */
    public int readInt() {
        while (true) {
            String line = readLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("input number(YO starii boh zdes)");
            }
        }
    }
}