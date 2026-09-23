package ru.nsu.wits.task_1_1_2.models;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private Card c(Rank r) { return new Card(r, Suit.SPADES); }

    @Test void addAndGetCardsWorkAndListIsUnmodifiable() {
        Hand hand = new Hand();
        hand.add(c(Rank.TWO));
        assertEquals(1, hand.getCards().size());
        assertEquals(Rank.TWO, hand.getCards().get(0).getRank());
        assertThrows(UnsupportedOperationException.class, () -> hand.getCards().clear());
    }

    @Test void clearRemovesAllCards() {
        Hand hand = new Hand();
        hand.add(c(Rank.TWO));
        hand.clear();
        assertTrue(hand.getCards().isEmpty());
        assertEquals(0, hand.getScore());
    }

    @Test void scoreUsesAceAsElevenWhenPossible() {
        Hand hand = new Hand();
        hand.add(c(Rank.ACE));
        hand.add(c(Rank.SIX));
        assertEquals(17, hand.getScore());
    }

    @Test void scoreConvertsAceToOneWhenNeeded() {
        Hand hand = new Hand();
        hand.add(c(Rank.ACE));
        hand.add(c(Rank.KING));
        hand.add(c(Rank.FIVE));
        assertEquals(16, hand.getScore());
    }

    @Test void multipleAcesAreAdjustedAsNeeded() {
        Hand hand = new Hand();
        hand.add(c(Rank.ACE));
        hand.add(c(Rank.ACE));
        hand.add(c(Rank.NINE));
        assertEquals(21, hand.getScore());
    }

    @Test void blackjackRequiresExactlyTwoCardsAndScore21() {
        Hand hand = new Hand();
        hand.add(c(Rank.ACE));
        hand.add(c(Rank.KING));
        assertTrue(hand.isBlackjack());

        hand.add(c(Rank.TWO));
        assertFalse(hand.isBlackjack());
    }

    @Test void bustIsTrueOnlyAbove21() {
        Hand hand = new Hand();
        hand.add(c(Rank.KING));
        hand.add(c(Rank.QUEEN));
        hand.add(c(Rank.TWO));
        assertTrue(hand.isBust());

        hand.clear();
        hand.add(c(Rank.KING));
        hand.add(c(Rank.ACE));
        assertFalse(hand.isBust());
    }

    @Test void toStringContainsCardsAndScore() {
        Hand hand = new Hand();
        hand.add(c(Rank.TEN));
        assertEquals("[Десятка Пики (10)] > 10", hand.toString());
    }
}