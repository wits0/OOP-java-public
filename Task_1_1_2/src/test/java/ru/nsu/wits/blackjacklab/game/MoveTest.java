package ru.nsu.wits.blackjacklab.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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