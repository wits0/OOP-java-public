package ru.nsu.wits.task_1_1_2.blackjack;
import ru.nsu.wits.task_1_1_2.game.Move;
/**
 * Common part for all game participants: a player and a dealer.
 * Holds a name and a hand, and knows how to take cards.
 * The decision to take one more card is made by subclasses.
 */
public abstract class PlayerAbstract {

    /** Name of the participant, used in messages. */
    private final String name;

    /** Cards in the participant's hand. */
    private final Hand hand = new Hand();

    /**
     * @param name name of the participant
     */
    protected PlayerAbstract(String name) {
        this.name = name;
    }

    /**
     * @return name of the participant
     */
    public String getName() {
        return name;
    }

    /**
     * @return the hand of the participant
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Adds a card to the participant's hand.
     *
     * @param card card to add
     */
    public void takeCard(Card card) {
        hand.add(card);
    }

    /**
     * @return {@code true} if the participant went over 21
     */
    public boolean isBust() {
        return hand.isBust();
    }

    /**
     * @return {@code true} if the participant has a blackjack
     */
    public boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    /**
     * Decides what to do next: take one more card or stop.
     *
     * @return the decision of the participant
     */
    public abstract Move decideMove();
}

