package ru.nsu.wits.blackjacklab.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void constructorAndGettersExposeCardData() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        assertSame(Rank.ACE, card.getRank());
        assertSame(Suit.SPADES, card.getSuit());
    }

    @Test
    void toStringContainsRankSuitAndValue() {
        assertEquals("Туз Пики (11)", new Card(Rank.ACE, Suit.SPADES).toString());
    }
}