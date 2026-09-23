package ru.nsu.wits.blackjacklab.iostuff;

import java.util.List;
import ru.nsu.wits.blackjacklab.models.Card;
import ru.nsu.wits.blackjacklab.models.Hand;
import ru.nsu.wits.blackjacklab.models.Participant;

/**
 * Prints hands of the participants.
 */
public class HandView {

    /**
     * Text shown instead of the hidden dealer card.
     */
    private static final String HIDDEN_CARD = "<закрытая карта>";

    /**
     * Prints the player's hand with the score.
     *
     * @param player the player
     */
    public void showPlayerHand(Participant player) {
        System.out.println("Ваши карты: " + player.getHand());
    }

    /**
     * Prints the dealer's hand.
     * If the hole card is hidden, only the first card and a placeholder are shown,
     * and no score is printed.
     * If the hole card is open, the full hand with the score is printed.
     *
     * @param dealer the dealer
     * @param holeCardHidden {@code true} if the second card should be hidden
     */
    public void showDealerHand(Participant dealer, boolean holeCardHidden) {
        Hand hand = dealer.getHand();

        System.out.print("Карты дилера: ");

        if (!holeCardHidden) {
            System.out.println(hand);
            return;
        }

        List<Card> cards = hand.getCards();
        Card first = cards.get(0);
        System.out.println("[" + first + ", " + HIDDEN_CARD + "]");
    }
}
