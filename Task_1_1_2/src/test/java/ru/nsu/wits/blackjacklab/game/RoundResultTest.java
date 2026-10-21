package ru.nsu.wits.blackjacklab.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Test of Round.
 */
class RoundResultTest {
    @Test
    void enumContainsAllResults() {
        assertArrayEquals(new RoundResult[]{
                RoundResult.PLAYER_WIN, RoundResult.DEALER_WIN, RoundResult.DRAW,
                RoundResult.PLAYER_BLACKJACK, RoundResult.DEALER_BLACKJACK
        }, RoundResult.values());
        for (RoundResult result : RoundResult.values()) {
            assertSame(result, RoundResult.valueOf(result.name()));
        }
    }
}