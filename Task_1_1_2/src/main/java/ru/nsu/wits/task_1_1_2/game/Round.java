// CHECKSTYLE.OFF: PackageName
package ru.nsu.wits.task_1_1_2.game;
// CHECKSTYLE.ON: PackageName

import ru.nsu.wits.task_1_1_2.iostuff.View;
import ru.nsu.wits.task_1_1_2.models.Card;
import ru.nsu.wits.task_1_1_2.models.Dealer;
import ru.nsu.wits.task_1_1_2.models.Deck;
import ru.nsu.wits.task_1_1_2.models.Player;

/**
 * One round of blackjack: deal, moves, compare, result.
 */
public class Round {

    /** How many cards each participant gets at the start of a round. */
    private static final int INITIAL_CARDS = 2;

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final View view;

    /**
     * @param deck the deck to draw cards from
     * @param player the player
     * @param dealer the dealer
     * @param view the view to print messages
     */
    public Round(Deck deck, Player player, Dealer dealer, View view) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.view = view;
    }

    /**
     * Plays one round and returns the result.
     *
     * @param roundNumber number of the round, for messages
     * @return the result of the round
     */
    public RoundResult play(int roundNumber) {
        view.showRoundStart(roundNumber);
        dealCards();
        view.getHandView().showPlayerHand(player);
        view.getHandView().showDealerHand(dealer, true);

        if (player.hasBlackjack() || dealer.hasBlackjack()) {
            return finishWithBlackjack();
        }

        RoundResult playerResult = playPlayerTurn();
        if (playerResult != null) {
            return playerResult;
        }

        RoundResult dealerResult = playDealerTurn();
        if (dealerResult != null) {
            return dealerResult;
        }

        return compareScores();
    }

    private void dealCards() {
        for (int i = 0; i < INITIAL_CARDS; i++) {
            player.takeCard(deck.draw());
            dealer.takeCard(deck.draw());
        }
    }

    private RoundResult finishWithBlackjack() {
        if (player.hasBlackjack() && dealer.hasBlackjack()) {
            return RoundResult.DRAW;
        }
        if (player.hasBlackjack()) {
            return RoundResult.PLAYER_BLACKJACK;
        }
        return RoundResult.DEALER_BLACKJACK;
    }

    private RoundResult playPlayerTurn() {
        view.showPlayerMoveStart();
        while (player.decideMove() == Move.HIT) {
            Card card = deck.draw();
            player.takeCard(card);
            view.showPlayerTakesCard(card);
            view.getHandView().showPlayerHand(player);
            view.getHandView().showDealerHand(dealer, true);
            if (player.isBust()) {
                return RoundResult.DEALER_WIN;
            }
        }
        return null;
    }

    private RoundResult playDealerTurn() {
        view.showDealerMoveStart();
        view.getHandView().showDealerHand(dealer, false);
        while (dealer.decideMove() == Move.HIT) {
            Card card = deck.draw();
            dealer.takeCard(card);
            view.showDealerOpensCard(card);
            view.getHandView().showDealerHand(dealer, false);
        }
        if (dealer.isBust()) {
            return RoundResult.PLAYER_WIN;
        }
        return null;
    }

    private RoundResult compareScores() {
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();
        if (playerScore > dealerScore) {
            return RoundResult.PLAYER_WIN;
        }
        if (playerScore < dealerScore) {
            return RoundResult.DEALER_WIN;
        }
        return RoundResult.DRAW;
    }
}
