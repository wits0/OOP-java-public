package ru.nsu.wits.expressionlab.modules;

/**
 * Addition of two expressions.
 */
public class Add extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * @param left left operand
     * @param right right operand
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + "+" + right + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public int eval(String assignments) {
        return left.eval(assignments) + right.eval(assignments);
    }
}