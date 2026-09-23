package ru.nsu.wits.task_1_1_2.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import ru.nsu.wits.task_1_1_2.iostuff.View;
import ru.nsu.wits.task_1_1_2.models.Card;
import ru.nsu.wits.task_1_1_2.models.Deck;
import ru.nsu.wits.task_1_1_2.models.Player;
import ru.nsu.wits.task_1_1_2.models.Dealer;
import ru.nsu.wits.task_1_1_2.models.Rank;
import ru.nsu.wits.task_1_1_2.models.Suit;

/** Tests the game loop and round reset. */
class GameTest {

    /** Checks that a completed round is scored and both hands are reset. */
    @Test
    void startScoresRoundAndResetsHands() {
        Player player = new Player("Player", new java.util.Scanner("0\n"));
        Dealer dealer = new Dealer("Dealer");
        Deck deck = new FixedDeck(
                card(Rank.ACE), card(Rank.TEN), card(Rank.KING), card(Rank.SIX));
        StopAfterSecondRoundView view = new StopAfterSecondRoundView(player, dealer);
        Game game = new Game(deck, player, dealer, view);

        assertThrows(StopGame.class, game::start);

        assertEquals(0, player.getHand().getCards().size());
        assertEquals(0, dealer.getHand().getCards().size());
        assertEquals(1, view.playerWins);
        assertEquals(0, view.dealerWins);
    }

    /** Creates a card with a fixed suit. */
    private static Card card(Rank rank) {
        return new Card(rank, Suit.SPADES);
    }

    /** Deck with a deterministic sequence of cards. */
    private static class FixedDeck extends Deck {
        private final Deque<Card> cards;

        /** Creates a deterministic deck. */
        FixedDeck(Card... cards) {
            super(1);
            this.cards = new ArrayDeque<>(Arrays.asList(cards));
        }

        @Override
        public Card draw() {
            return cards.removeFirst();
        }
    }

    /** Stops the infinite game loop after the first round has been reset. */
    private static class StopAfterSecondRoundView extends View {
        private final Player player;
        private final Dealer dealer;
        private int playerWins;
        private int dealerWins;

        /** Creates a view that checks the hands before the second round.
         */
        StopAfterSecondRoundView(Player player, Dealer dealer) {
            this.player = player;
            this.dealer = dealer;
        }

        @Override
        public void showRoundStart(int roundNumber) {
            if (roundNumber == 2) {
                assertEquals(0, player.getHand().getCards().size());
                assertEquals(0, dealer.getHand().getCards().size());
                throw new StopGame();
            }
        }

        @Override
        public void showScore(int playerWins, int dealerWins) {
            this.playerWins = playerWins;
            this.dealerWins = dealerWins;
        }
    }

    /** Runtime exception used to stop the infinite game loop in the test. */
    private static class StopGame extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
