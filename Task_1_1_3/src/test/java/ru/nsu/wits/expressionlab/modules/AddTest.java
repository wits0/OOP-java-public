package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void toStringWrapsInParentheses() {
        Expression expression = new Add(new Number(3), new Number(2));
        assertEquals("(3+2)", expression.toString());
    }

    @Test
    void derivativeFollowsSumRule() {
        Expression expression = new Add(new Number(3), new Variable("x"));
        assertEquals("(0+1)", expression.derivative("x").toString());
    }

    @Test
    void evalSumsOperands() {
        Expression expression = new Add(new Number(3), new Number(2));
        assertEquals(5, expression.eval(""));
    }
}
