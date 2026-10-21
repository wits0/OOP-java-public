package ru.nsu.wits.expressionlab.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses a string with variable assignments like "x = 10; y = 13".
 */
public final class AssignmentsParser {

    private AssignmentsParser() {
    }

    /**
     * Parses the given assignments string.
     *
     * @param input string with assignments separated by ';'
     * @return map from variable name to value
     */
    public static Map<String, Integer> parse(String input) {
        Map<String, Integer> result = new HashMap<>();
        List<String> parts = new ArrayList<>(Arrays.asList(input.split(";")));
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            List<String> pair = new ArrayList<>(Arrays.asList(trimmed.split("=")));
            if (pair.size() != 2) {
                throw new IllegalArgumentException("Bad assignment: " + part);
            }
            String name = pair.get(0).trim();
            int value = Integer.parseInt(pair.get(1).trim());
            result.put(name, value);
        }
        return result;
    }
}
