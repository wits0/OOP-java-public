package ru.nsu.wits.expressionlab.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OperatorTest {

    @Test
    void getSymbolReturnsCharacter() {
        assertEquals('+', Operator.ADD.getSymbol());
    }

    @Test
    void isOperatorRecognizesSymbols() {
        assertTrue(Operator.isOperator('+'));
        assertTrue(Operator.isOperator('-'));
        assertTrue(Operator.isOperator('*'));
        assertTrue(Operator.isOperator('/'));
        assertFalse(Operator.isOperator('^'));
    }

    @Test
    void fromSymbolReturnsMatchingOperator() {
        assertEquals(Operator.ADD, Operator.fromSymbol('+'));
        assertEquals(Operator.SUB, Operator.fromSymbol('-'));
        assertEquals(Operator.MUL, Operator.fromSymbol('*'));
        assertEquals(Operator.DIV, Operator.fromSymbol('/'));
    }

    @Test
    void applyBuildsBinaryExpression() {
        Expression expression = Operator.ADD.apply(new Number(1), new Number(2));
        assertEquals("(1+2)", expression.toString());
    }
}
