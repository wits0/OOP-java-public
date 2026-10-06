package ru.nsu.wits.expressionlab.parsers;

import ru.nsu.wits.expressionlab.modules.Add;
import ru.nsu.wits.expressionlab.modules.Div;
import ru.nsu.wits.expressionlab.modules.Expression;
import ru.nsu.wits.expressionlab.modules.Mul;
import ru.nsu.wits.expressionlab.modules.Number;
import ru.nsu.wits.expressionlab.modules.Sub;
import ru.nsu.wits.expressionlab.modules.Variable;

/**
 * Parses a string form of an expression into an Expression tree.
 * Every binary operation in the input must be surrounded by parentheses.
 */
public final class ExpressionParser {

    private ExpressionParser() {
    }

    /**
     * Parses the given string into an expression.
     *
     * @param input string form of an expression, for example "(3+(2*x))"
     * @return parsed expression
     */
    public static Expression parse(String input) {
        String s = removeSpaces(input);

        if (isBinaryExpression(s)) {
            String inner = s.substring(1, s.length() - 1);
            int operatorIndex = findTopLevelOperator(inner);
            char operator = inner.charAt(operatorIndex);

            String leftPart = inner.substring(0, operatorIndex);
            String rightPart = inner.substring(operatorIndex + 1);

            Expression left = parse(leftPart);
            Expression right = parse(rightPart);

            return buildBinary(operator, left, right);
        }

        if (isNumber(s)) {
            return new Number(Integer.parseInt(s));
        }

        return new Variable(s);
    }

    /**
     * Removes all whitespace characters from the given string.
     *
     * @param input source string
     * @return string without whitespace
     */
    private static String removeSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

    /**
     * Checks if the given string is a binary expression in parentheses.
     *
     * @param s string to check
     * @return true if it starts with '(' and ends with ')'
     */
    private static boolean isBinaryExpression(String s) {
        return s.startsWith("(") && s.endsWith(")");
    }

    /**
     * Finds the index of the top-level operator inside the given string.
     * Top-level means it is not inside any nested parentheses.
     *
     * @param inner string without outer parentheses
     * @return index of the operator
     */
    private static int findTopLevelOperator(String inner) {
        int depth = 0;
        for (int i = 0; i < inner.length(); i++) {
            char c = inner.charAt(i);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && isOperator(c)) {
                return i;
            }
        }
        throw new IllegalArgumentException("No top-level operator found: " + inner);
    }

    /**
     * Checks if the given character is a supported binary operator.
     *
     * @param c character to check
     * @return true for '+', '-', '*', '/'
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Checks if the given string is a number.
     *
     * @param s string to check
     * @return true if the string can be parsed as an integer
     */
    private static boolean isNumber(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds a binary expression for the given operator.
     *
     * @param operator one of '+', '-', '*', '/'
     * @param left left operand
     * @param right right operand
     * @return matching binary expression
     */
    private static Expression buildBinary(char operator, Expression left, Expression right) {
        switch (operator) {
            case '+':
                return new Add(left, right);
            case '-':
                return new Sub(left, right);
            case '*':
                return new Mul(left, right);
            case '/':
                return new Div(left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}