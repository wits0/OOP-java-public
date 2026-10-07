package ru.nsu.wits.expressionlab.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class AssignmentsParserTest {

    @Test
    void parsesSingleAssignment() {
        Map<String, Integer> result = AssignmentsParser.parse("x = 10");
        assertEquals(10, result.get("x"));
    }

    @Test
    void parsesMultipleAssignments() {
        Map<String, Integer> result = AssignmentsParser.parse("x = 10; y = 13");
        assertEquals(10, result.get("x"));
        assertEquals(13, result.get("y"));
    }

    @Test
    void handlesExtraSpaces() {
        Map<String, Integer> result = AssignmentsParser.parse("  x=5  ;  y = 7 ");
        assertEquals(5, result.get("x"));
        assertEquals(7, result.get("y"));
    }
}
