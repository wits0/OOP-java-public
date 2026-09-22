package ru.nsu.wits.task_1_1_2.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A hand of cards. Belongs to a player or a dealer.
 * Counts the score and knows about blackjack and bust.
 */
public class Hand {

    /** Score of 21 — the best score in blackjack. */
    private static final int BLACKJACK_SCORE = 21;

    /** How much we subtract from an ace when it must count as 1 instead of 11. */
    private static final int ACE_HIGH_TO_LOW_DIFF = 10;

    /** Number of cards in a blackjack combination. */
    private static final int BLACKJACK_CARDS_COUNT = 2;

    /** Cards in this hand, in the order they were taken. */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Adds a card to the hand.
     *
     * @param card card to add
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Removes all cards from the hand. Used between rounds.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Returns the cards in this hand. The list cannot be changed from outside.
     *
     * @return unmodifiable list of cards
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Counts the score of the hand.
     * Aces are counted as 11 while the total score is not more than 21,
     * and as 1 if the score would be too big.
     *
     * @return score of the hand
     */
    public int getScore() {
        int score = 0;
        int aces = 0;

        for (Card card : cards) {
            score += card.getRank().getValueCard();
            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }

        while (score > BLACKJACK_SCORE && aces > 0) {
            score -= ACE_HIGH_TO_LOW_DIFF;
            aces--;
        }

        return score;
    }

    /**
     * @return {@code true} if the hand has exactly two cards and they give 21
     */
    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_COUNT && getScore() == BLACKJACK_SCORE;
    }

    /**
     * @return {@code true} if the score of the hand is bigger than 21
     */
    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    @Override
    public String toString() {
        return cards + " > " + getScore();
    }
}
