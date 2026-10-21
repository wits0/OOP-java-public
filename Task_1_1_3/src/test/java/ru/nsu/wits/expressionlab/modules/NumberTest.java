package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void toStringReturnsValue() {
        Number number = new Number(42);
        assertEquals("42", number.toString());
    }

    @Test
    void derivativeIsZero() {
        Number number = new Number(5);
        assertEquals(new Number(0).toString(), number.derivative("x").toString());
    }

    @Test
    void evalReturnsValue() {
        Number number = new Number(7);
        assertEquals(7, number.eval(""));
    }
}
