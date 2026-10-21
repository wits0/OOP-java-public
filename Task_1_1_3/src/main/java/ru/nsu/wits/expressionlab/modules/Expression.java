package ru.nsu.wits.expressionlab.modules;

/**
 * Base class for all mathematical expressions.
 */
public abstract class Expression {

    /**
     * Prints this expression to the standard output.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Returns a string form of this expression.
     *
     * @return string form
     */
    @Override
    public abstract String toString();

    /**
     * Builds a new expression that is the derivative
     * of this expression by the given variable.
     *
     * @param variable name of the variable
     * @return new derivative expression
     */
    public abstract Expression derivative(String variable);

    /**
     * Evaluates this expression with the given variable values.
     * The string has the form "x = 10; y = 13".
     *
     * @param assignments variable values
     * @return result of evaluation
     */
    public abstract int eval(String assignments);
}