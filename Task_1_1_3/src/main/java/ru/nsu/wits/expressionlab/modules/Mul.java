package ru.nsu.wits.expressionlab.modules;

/**
 * Multiplication of two expressions.
 */
public class Mul extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * @param left left operand
     * @param right right operand
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + "*" + right + ")";
    }

    @Override
    public Expression derivative(String variable) {
        Expression leftPrime = left.derivative(variable);
        Expression rightPrime = right.derivative(variable);
        return new Add(
                new Mul(leftPrime, right),
                new Mul(left, rightPrime)
        );
    }

    @Override
    public int eval(String assignments) {
        return left.eval(assignments) * right.eval(assignments);
    }
}