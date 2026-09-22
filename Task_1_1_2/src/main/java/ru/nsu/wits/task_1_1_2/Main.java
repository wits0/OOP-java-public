package ru.nsu.wits.task_1_1_2;

import java.util.Scanner;

import ru.nsu.wits.task_1_1_2.blackjack.Dealer;
import ru.nsu.wits.task_1_1_2.blackjack.Deck;
import ru.nsu.wits.task_1_1_2.blackjack.Player;
import ru.nsu.wits.task_1_1_2.game.Game;
import ru.nsu.wits.task_1_1_2.iostuff.View;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck(1);
        Player player = new Player("Игрок", scanner);
        Dealer dealer = new Dealer("Дилер");
        View view = new View();

        Game game = new Game(deck, player, dealer, view);
        game.start();
    }
}