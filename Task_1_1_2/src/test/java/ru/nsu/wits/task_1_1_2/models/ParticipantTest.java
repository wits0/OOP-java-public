package ru.nsu.wits.task_1_1_2.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.wits.task_1_1_2.game.Move;

class ParticipantTest {
    private static class TestParticipant extends Participant {
        TestParticipant(String name) {
            super(name);
        }

        @Override
        public Move decideMove() {
            return Move.STAND;
        }
    }

    @Test
    void nameAndHandAreExposed() {
        TestParticipant p = new TestParticipant("Test");
        assertEquals("Test", p.getName());
        assertNotNull(p.getHand());
    }

    @Test
    void takeCardAddsCardAndStatusMethodsDelegateToHand() {
        TestParticipant p = new TestParticipant("Test");
        p.takeCard(new Card(Rank.ACE, Suit.SPADES));
        p.takeCard(new Card(Rank.KING, Suit.HEARTS));
        assertEquals(2, p.getHand().getCards().size());
        assertTrue(p.hasBlackjack());
        assertFalse(p.isBust());
    }

    @Test
    void decideMoveCanBeImplementedBySubclass() {
        assertEquals(Move.STAND, new TestParticipant("Test").decideMove());
    }
}