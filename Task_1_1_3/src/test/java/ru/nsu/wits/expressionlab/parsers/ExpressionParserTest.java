package ru.nsu.wits.expressionlab.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.wits.expressionlab.modules.Expression;

class ExpressionParserTest {

    @Test
    void parsesNumber() {
        Expression expression = ExpressionParser.parse("42");
        assertEquals("42", expression.toString());
    }

    @Test
    void parsesVariable() {
        Expression expression = ExpressionParser.parse("x");
        assertEquals("x", expression.toString());
    }

    @Test
    void parsesAddition() {
        Expression expression = ExpressionParser.parse("(3+2)");
        assertEquals("(3+2)", expression.toString());
    }

    @Test
    void parsesNestedExpression() {
        Expression expression = ExpressionParser.parse("(3+(2*x))");
        assertEquals("(3+(2*x))", expression.toString());
    }

    @Test
    void parsesWithSpaces() {
        Expression expression = ExpressionParser.parse(" ( 3 + ( 2 * x ) ) ");
        assertEquals("(3+(2*x))", expression.toString());
    }
}
