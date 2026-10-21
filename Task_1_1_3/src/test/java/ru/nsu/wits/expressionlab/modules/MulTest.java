package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void toStringWrapsInParentheses() {
        Expression expression = new Mul(new Number(3), new Number(2));
        assertEquals("(3*2)", expression.toString());
    }

    @Test
    void derivativeFollowsProductRule() {
        Expression expression = new Mul(new Number(2), new Variable("x"));
        assertEquals("((0*x)+(2*1))", expression.derivative("x").toString());
    }

    @Test
    void evalMultipliesOperands() {
        Expression expression = new Mul(new Number(3), new Number(2));
        assertEquals(6, expression.eval(""));
    }
}
