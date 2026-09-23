package ru.nsu.wits.task_1_1_2.models;

import org.junit.jupiter.api.Test;

import ru.nsu.wits.task_1_1_2.game.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {
    @Test
    void dealerHitsBelow17() {
        Dealer dealer = new Dealer("Дилер");
        dealer.takeCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.takeCard(new Card(Rank.SIX, Suit.HEARTS));
        assertEquals(16, dealer.getHand().getScore());
        assertEquals(Move.HIT, dealer.decideMove());
    }

    @Test
    void dealerStandsAt17OrMore() {
        Dealer dealer = new Dealer("Дилер");
        dealer.takeCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.takeCard(new Card(Rank.SEVEN, Suit.HEARTS));
        assertEquals(Move.STAND, dealer.decideMove());
    }
}