package ru.nsu.wits.expressionlab.modules;

/**
 * Division of two expressions.
 */
public class Div extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * @param left left operand
     * @param right right operand
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + "/" + right + ")";
    }

    @Override
    public Expression derivative(String variable) {
        Expression leftPrime = left.derivative(variable);
        Expression rightPrime = right.derivative(variable);
        // (f / g)' = (f' * g - f * g') / (g * g)
        return new Div(
                new Sub(
                        new Mul(leftPrime, right),
                        new Mul(left, rightPrime)
                ),
                new Mul(right, right)
        );
    }

    @Override
    public int eval(String assignments) {
        return left.eval(assignments) / right.eval(assignments);
    }
}