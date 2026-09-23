// CHECKSTYLE.OFF: PackageName
package ru.nsu.wits.task_1_1_2;
// CHECKSTYLE.ON: PackageName

import java.util.Random;
import java.util.Scanner;

import ru.nsu.wits.task_1_1_2.game.Game;
import ru.nsu.wits.task_1_1_2.iostuff.View;
import ru.nsu.wits.task_1_1_2.models.Dealer;
import ru.nsu.wits.task_1_1_2.models.Deck;
import ru.nsu.wits.task_1_1_2.models.Player;

/**
 * Entry point of the blackjack application.
 */
public class Main {

    private static final int MIN_DECKS = 3;
    private static final int MAX_DECKS = 6;

    /**
     * Starts the blackjack application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int deckCount = MIN_DECKS + random.nextInt(MAX_DECKS - MIN_DECKS + 1);
        Deck deck = new Deck(deckCount);
        Player player = new Player("Игрок", scanner);
        Dealer dealer = new Dealer("Дилер");
        View view = new View();

        Game game = new Game(deck, player, dealer, view);
        game.start();
    }
}
