package ru.nsu.wits.task_1_1_2.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
    private Card card(Rank rank) {
        return new Card(rank, Suit.SPADES);
    }

    @Test
    void addAndGetCardsWorkAndListIsUnmodifiable() {
        Hand hand = new Hand();
        hand.add(card(Rank.TWO));
        assertEquals(1, hand.getCards().size());
        assertEquals(Rank.TWO, hand.getCards().get(0).getRank());
        assertThrows(UnsupportedOperationException.class, () -> hand.getCards().clear());
    }

    @Test
    void clearRemovesAllCards() {
        Hand hand = new Hand();
        hand.add(card(Rank.TWO));
        hand.clear();
        assertTrue(hand.getCards().isEmpty());
        assertEquals(0, hand.getScore());
    }

    @Test
    void scoreUsesAceAsElevenWhenPossible() {
        Hand hand = new Hand();
        hand.add(card(Rank.ACE));
        hand.add(card(Rank.SIX));
        assertEquals(17, hand.getScore());
    }

    @Test
    void scoreConvertsAceToOneWhenNeeded() {
        Hand hand = new Hand();
        hand.add(card(Rank.ACE));
        hand.add(card(Rank.KING));
        hand.add(card(Rank.FIVE));
        assertEquals(16, hand.getScore());
    }

    @Test
    void multipleAcesAreAdjustedAsNeeded() {
        Hand hand = new Hand();
        hand.add(card(Rank.ACE));
        hand.add(card(Rank.ACE));
        hand.add(card(Rank.NINE));
        assertEquals(21, hand.getScore());
    }

    @Test
    void blackjackRequiresExactlyTwoCardsAndScore21() {
        Hand hand = new Hand();
        hand.add(card(Rank.ACE));
        hand.add(card(Rank.KING));
        assertTrue(hand.isBlackjack());

        hand.add(card(Rank.TWO));
        assertFalse(hand.isBlackjack());
    }

    @Test
    void bustIsTrueOnlyAbove21() {
        Hand hand = new Hand();
        hand.add(card(Rank.KING));
        hand.add(card(Rank.QUEEN));
        hand.add(card(Rank.TWO));
        assertTrue(hand.isBust());

        hand.clear();
        hand.add(card(Rank.KING));
        hand.add(card(Rank.ACE));
        assertFalse(hand.isBust());
    }

    @Test
    void toStringContainsCardsAndScore() {
        Hand hand = new Hand();
        hand.add(card(Rank.TEN));
        assertEquals("[Десятка Пики (10)] > 10", hand.toString());
    }
}