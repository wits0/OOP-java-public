package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void toStringReturnsName() {
        Variable variable = new Variable("x");
        assertEquals("x", variable.toString());
    }

    @Test
    void derivativeBySameVariableIsOne() {
        Variable variable = new Variable("x");
        assertEquals(new Number(1).toString(), variable.derivative("x").toString());
    }

    @Test
    void derivativeByOtherVariableIsZero() {
        Variable variable = new Variable("x");
        assertEquals(new Number(0).toString(), variable.derivative("y").toString());
    }

    @Test
    void evalReturnsAssignedValue() {
        Variable variable = new Variable("x");
        assertEquals(10, variable.eval("x = 10"));
    }
}
