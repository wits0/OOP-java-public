package ru.nsu.wits.task_1_1_2.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreCounterTest {
    @Test void startsAtZero() {
        ScoreCounter counter = new ScoreCounter();
        assertEquals(0, counter.getPlayerWins());
        assertEquals(0, counter.getDealerWins());
    }

    @Test void incrementsPlayerWins() {
        ScoreCounter counter = new ScoreCounter();
        counter.addPlayerWin();
        counter.addPlayerWin();
        assertEquals(2, counter.getPlayerWins());
    }

    @Test void incrementsDealerWins() {
        ScoreCounter counter = new ScoreCounter();
        counter.addDealerWin();
        counter.addDealerWin();
        counter.addDealerWin();
        assertEquals(3, counter.getDealerWins());
    }
}