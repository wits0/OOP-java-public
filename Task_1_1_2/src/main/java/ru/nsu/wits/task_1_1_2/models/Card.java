// CHECKSTYLE.OFF: PackageName
package ru.nsu.wits.task_1_1_2.models;
// CHECKSTYLE.ON: PackageName

/**
 * A playing card with a rank and a suit.
 */
public class Card {

    private final Rank rank;
    private final Suit suit;

    /**
     * Creates a card.
     *
     * @param rank card rank
     * @param suit card suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * @return card rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * @return card suit
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank.getNameCard() + " " + suit.getRuName() + " (" + rank.getValueCard() + ")";
    }
}
