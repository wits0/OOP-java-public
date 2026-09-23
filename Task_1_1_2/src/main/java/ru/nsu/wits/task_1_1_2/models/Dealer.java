package ru.nsu.wits.task_1_1_2.models;
import ru.nsu.wits.task_1_1_2.game.Move;

/**
 * The dealer. Takes cards while the score is less than 17.
 */
public class Dealer extends Participant {

    /** The dealer must take cards while the score is below this value. */
    private static final int DEALER_STAND_SCORE = 17;

    /**
     * @param name name of the dealer
     */
    public Dealer(String name) {
        super(name);
    }

    @Override
    public Move decideMove() {
        if (getHand().getScore() < DEALER_STAND_SCORE) {
            return Move.HIT;
        }
        return Move.STAND;
    }
}