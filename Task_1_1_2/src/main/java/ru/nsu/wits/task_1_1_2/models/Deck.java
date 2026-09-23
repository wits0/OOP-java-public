// CHECKSTYLE.OFF: PackageName
package ru.nsu.wits.task_1_1_2.models;
// CHECKSTYLE.ON: PackageName

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * A deck of cards. May contain one or more standard 52-card decks.
 * Can be shuffled and can give cards one by one.
 */
public class Deck {

    /** Number of cards in one standard deck (4 suits × 13 ranks). */
    private static final int CARDS_IN_SINGLE_DECK = 52;

    /** Cards that are not dealt yet. We take from the front. */
    private final Deque<Card> cards = new ArrayDeque<>();

    /** How many standard decks this deck contains. */
    private final int deckCount;

    /**
     * Creates a deck with the given number of standard decks.
     * The deck is filled and shuffled right away.
     *
     * @param deckCount how many standard decks to use, must be positive
     * @throws IllegalArgumentException if deckCount is not positive
     */
    public Deck(int deckCount) {
        this.deckCount = deckCount;
        refill();
        shuffle();
    }

    /**
     * Gives the top card from the deck.
     * If the deck is empty, it is refilled and shuffled again.
     *
     * @return the top card
     */
    public Card draw() {
        if (cards.isEmpty()) {
            refill();
            shuffle();
        }
        return cards.removeFirst();
    }

    /**
     * Fills the deck with {@code deckCount * 52} new cards.
     */
    private void refill() {
        cards.clear();
        for (int i = 0; i < deckCount; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.addLast(new Card(rank, suit));
                }
            }
        }
    }

    /**
     * Shuffles the deck in random order.
     */
    private void shuffle() {
        List<Card> list = new ArrayList<>(cards);
        Collections.shuffle(list);
        cards.clear();
        cards.addAll(list);
    }

    /**
     * @return number of cards left in the deck
     */
    int size() {
        return cards.size();
    }

    /**
     * @return {@code true} if there are no cards left
     */
    boolean isEmpty() {
        return cards.isEmpty();
    }
}
