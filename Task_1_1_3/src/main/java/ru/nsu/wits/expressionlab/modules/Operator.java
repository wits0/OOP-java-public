package ru.nsu.wits.expressionlab.modules;

/**
 * Binary operators supported by the expression language.
 * Each operator knows its symbol and can build an expression from two operands.
 */
public enum Operator {

    ADD('+') {
        @Override
        public Expression apply(Expression left, Expression right) {
            return new Add(left, right);
        }
    },
    SUB('-') {
        @Override
        public Expression apply(Expression left, Expression right) {
            return new Sub(left, right);
        }
    },
    MUL('*') {
        @Override
        public Expression apply(Expression left, Expression right) {
            return new Mul(left, right);
        }
    },
    DIV('/') {
        @Override
        public Expression apply(Expression left, Expression right) {
            return new Div(left, right);
        }
    };

    private final char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * @return symbol of this operator
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Builds an expression from two operands using this operator.
     *
     * @param left left operand
     * @param right right operand
     * @return expression
     */
    public abstract Expression apply(Expression left, Expression right);

    /**
     * Checks if the given character is a known operator symbol.
     *
     * @param c character to check
     * @return true if the character matches some operator
     */
    public static boolean isOperator(char c) {
        for (Operator op : Operator.values()) {
            if (op.symbol == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an operator by its symbol.
     *
     * @param symbol symbol to look up
     * @return matching operator
     * @throws IllegalArgumentException if no operator matches
     */
    public static Operator fromSymbol(char symbol) {
        for (Operator op : Operator.values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }
}