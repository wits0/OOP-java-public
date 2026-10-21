package ru.nsu.wits.expressionlab.modules;

import java.util.Map;

import ru.nsu.wits.expressionlab.parsers.AssignmentsParser;

/**
 * A variable expression.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * @param name name of the variable
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression derivative(String variable) {
        if (name.equals(variable)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public int eval(String assignments) {
        Map<String, Integer> values = AssignmentsParser.parse(assignments);
        if (!values.containsKey(name)) {
            throw new IllegalArgumentException("Variable not assigned: " + name);
        }
        return values.get(name);
    }
}