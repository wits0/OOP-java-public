package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void toStringWrapsInParentheses() {
        Expression expression = new Div(new Number(6), new Number(2));
        assertEquals("(6/2)", expression.toString());
    }

    @Test
    void derivativeFollowsQuotientRule() {
        Expression expression = new Div(new Variable("x"), new Number(2));
        assertEquals("(((1*2)-(x*0))/(2*2))", expression.derivative("x").toString());
    }

    @Test
    void evalDividesOperands() {
        Expression expression = new Div(new Number(6), new Number(2));
        assertEquals(3, expression.eval(""));
    }
}
