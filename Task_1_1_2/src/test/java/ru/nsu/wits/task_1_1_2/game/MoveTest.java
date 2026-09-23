package ru.nsu.wits.task_1_1_2.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Test for Move class.
 */
class MoveTest {
    @Test
    void enumContainsExpectedMoves() {
        assertArrayEquals(new Move[]{Move.HIT, Move.STAND}, Move.values());
        assertSame(Move.HIT, Move.valueOf("HIT"));
        assertSame(Move.STAND, Move.valueOf("STAND"));
    }
}