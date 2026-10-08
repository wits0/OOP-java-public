package ru.nsu.wits.expressionlab.modules;

/**
 * A constant number expression.
 */
public class Number extends Expression {

    private final int value;

    /**
     * @param value value of the constant
     */
    public Number(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public int eval(String assignments) {
        return value;
    }
}