package ru.nsu.wits.task_1_1_2.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoundResultTest {
    @Test void enumContainsAllResults() {
        assertArrayEquals(new RoundResult[]{
            RoundResult.PLAYER_WIN, RoundResult.DEALER_WIN, RoundResult.DRAW,
            RoundResult.PLAYER_BLACKJACK, RoundResult.DEALER_BLACKJACK
        }, RoundResult.values());
        for (RoundResult result : RoundResult.values()) {
            assertSame(result, RoundResult.valueOf(result.name()));
        }
    }
}