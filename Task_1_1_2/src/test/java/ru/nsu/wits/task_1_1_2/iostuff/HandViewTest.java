package ru.nsu.wits.task_1_1_2.iostuff;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.nsu.wits.task_1_1_2.models.Card;
import ru.nsu.wits.task_1_1_2.models.Dealer;
import ru.nsu.wits.task_1_1_2.models.Player;
import ru.nsu.wits.task_1_1_2.models.Rank;
import ru.nsu.wits.task_1_1_2.models.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test of HandView.
 */
class HandViewTest {
    private PrintStream original;
    private ByteArrayOutputStream out;

    @BeforeEach void capture() {
        original = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restore() {
        System.setOut(original);
    }

    @Test void playerHandIsPrinted() {
        Player player = new Player("Игрок", new java.util.Scanner("0\n"));
        player.takeCard(new Card(Rank.ACE, Suit.SPADES));
        new HandView().showPlayerHand(player);
        assertTrue(out.toString().contains("Ваши карты: [Туз Пики (11)] > 11"));
    }

    @Test void dealerHandCanBeHidden() {
        Dealer dealer = new Dealer("Дилер");
        dealer.takeCard(new Card(Rank.ACE, Suit.SPADES));
        dealer.takeCard(new Card(Rank.KING, Suit.HEARTS));
        new HandView().showDealerHand(dealer, true);
        String expected = "Карты дилера: [Туз Пики (11), <закрытая карта>]"
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    @Test void dealerHandCanBeShownFully() {
        Dealer dealer = new Dealer("Дилер");
        dealer.takeCard(new Card(Rank.ACE, Suit.SPADES));
        dealer.takeCard(new Card(Rank.KING, Suit.HEARTS));
        new HandView().showDealerHand(dealer, false);
        String expected = "Карты дилера: [Туз Пики (11), Король Черви (10)] > 21"
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }
}