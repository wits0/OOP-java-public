package ru.nsu.wits.task_1_1_2.models;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckTest {
    @Test
    void singleDeckHas52Cards() {
        Deck deck = new Deck(1);
        assertEquals(52, deck.size());
        assertFalse(deck.isEmpty());
    }

    @Test
    void multipleDecksHaveExpectedSize() {
        Deck deck = new Deck(3);
        assertEquals(156, deck.size());
    }

    @Test
    void drawingRemovesCardsUntilEmpty() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 51; i++) {
            assertNotNull(deck.draw());
        }
        assertEquals(1, deck.size());
        assertFalse(deck.isEmpty());
        assertNotNull(deck.draw());
        assertEquals(0, deck.size());
        assertTrue(deck.isEmpty());
    }

    @Test
    void drawingFromEmptyDeckRefillsAndShuffles() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.draw());
        }
        assertTrue(deck.isEmpty());
        assertNotNull(deck.draw());
        assertEquals(51, deck.size());
    }

    @Test
    void drawnCardsMakeUpSingleDeckBeforeRefill() {
        Deck deck = new Deck(1);
        Set<String> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw().toString());
        }
        assertEquals(52, cards.size());
    }

}