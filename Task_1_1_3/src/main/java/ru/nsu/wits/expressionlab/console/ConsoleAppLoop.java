package ru.nsu.wits.expressionlab.console;

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
        System.out.println("Welcome to the Expression Calculator!");
        while (true) {
            System.out.println();
            System.out.print("Enter an expression (or 'exit' to quit): ");
            String line = input.readLine();
            if (line.equals("exit")) {
                System.out.println("Bye!");
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
            System.out.println("Failed to parse the expression: " + e.getMessage());
            return;
        }
        System.out.println("Parsed: " + expression);

        while (true) {
            printMenu();
            int choice = input.readInt();
            if (choice == 0) {
                return;
            }
            switch (choice) {
                case 1:
                    System.out.println("Expression: " + expression);
                    break;
                case 2:
                    handleDerivative(expression);
                    break;
                case 3:
                    handleEval(expression);
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    private void handleDerivative(Expression expression) {
        System.out.print("Differentiate by which variable? ");
        String variable = input.readLine();
        Expression derivative = expression.derivative(variable);
        System.out.println("Derivative: " + derivative);
    }

    private void handleEval(Expression expression) {
        System.out.print("Enter assignments (for example \"x = 10; y = 13\"): ");
        String assignments = input.readLine();
        try {
            int result = expression.eval(assignments);
            System.out.println("Result: " + result);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1 - print the expression");
        System.out.println("2 - compute the derivative");
        System.out.println("3 - evaluate the expression");
        System.out.println("0 - enter a new expression");
        System.out.print("> ");
    }
}