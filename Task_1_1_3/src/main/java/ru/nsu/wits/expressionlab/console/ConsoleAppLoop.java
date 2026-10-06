package ru.nsu.wits.expressionlab.console;

import java.util.Scanner;

import ru.nsu.wits.expressionlab.modules.Expression;
import ru.nsu.wits.expressionlab.parsers.ExpressionParser;

/**
 * Interactive console application for working with expressions.
 */
public class ConsoleAppLoop {

    private final ConsoleInput input;

    /**
     * @param input source of user input
     */
    public ConsoleAppLoop(ConsoleInput input) {
        this.input = input;
    }

    /**
     * Runs the main dialog loop.
     */
    public void run() {
        System.out.println("Привет! Я сделал вывод с помощью дипсика потому что я долбаеб!!!");
        while (true) {
            System.out.println();
            System.out.print("Введите выражение (или 'exit' для выхода): ");
            String line = input.readLine();
            if (line.equals("exit")) {
                System.out.println("Пока!");
                return;
            }
            handleExpression(line);
        }
    }

    /**
     * Processes one expression entered by the user.
     *
     * @param line string form of the expression
     */
    private void handleExpression(String line) {
        Expression expression;
        try {
            expression = ExpressionParser.parse(line);
        } catch (RuntimeException e) {
            System.out.println("Не удалось распознать выражение: " + e.getMessage());
            return;
        }
        System.out.println("Распознано: " + expression);

        while (true) {
            printMenu();
            int choice = input.readInt();
            if (choice == 0) {
                return;
            }
            switch (choice) {
                case 1:
                    System.out.println("Выражение: " + expression);
                    break;
                case 2:
                    handleDerivative(expression);
                    break;
                case 3:
                    handleEval(expression);
                    break;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }

    private void handleDerivative(Expression expression) {
        System.out.print("По какой переменной? ");
        String variable = input.readLine();
        Expression derivative = expression.derivative(variable);
        System.out.println("Производная: " + derivative);
    }

    private void handleEval(Expression expression) {
        System.out.print("Введите означивание (например \"x = 10; y = 13\"): ");
        String assignments = input.readLine();
        try {
            int result = expression.eval(assignments);
            System.out.println("Результат: " + result);
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1 - вывести выражение");
        System.out.println("2 - найти производную");
        System.out.println("3 - вычислить значение");
        System.out.println("0 - ввести новое выражение");
        System.out.print("> ");
    }
}
