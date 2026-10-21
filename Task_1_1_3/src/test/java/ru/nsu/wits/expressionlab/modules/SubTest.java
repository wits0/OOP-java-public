package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void toStringWrapsInParentheses() {
        Expression expression = new Sub(new Number(5), new Number(2));
        assertEquals("(5-2)", expression.toString());
    }

    @Test
    void derivativeFollowsDifferenceRule() {
        Expression expression = new Sub(new Variable("x"), new Number(3));
        assertEquals("(1-0)", expression.derivative("x").toString());
    }

    @Test
    void evalSubtractsOperands() {
        Expression expression = new Sub(new Number(5), new Number(2));
        assertEquals(3, expression.eval(""));
    }
}
