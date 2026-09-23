package ru.nsu.wits.task_1_1_2.iostuff;

import ru.nsu.wits.task_1_1_2.models.Card;

/**
 * Prints all game messages to the console.
 */
public class View {

    private final HandView handView = new HandView();

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    /**
     * Prints the start of a round.
     *
     * @param roundNumber number of the round
     */
    public void showRoundStart(int roundNumber) {
        System.out.println();
        System.out.println("Раунд " + roundNumber);
        System.out.println("Дилер раздал карты");
    }

    /**
     * Prints the player's move header.
     */
    public void showPlayerMoveStart() {
        System.out.println();
        System.out.println("Ваш ход");
        System.out.println("-------");
    }

    /**
     * Prints the card the player has just taken.
     *
     * @param card the card
     */
    public void showPlayerTakesCard(Card card) {
        System.out.println("Вы открыли карту " + card);
    }

    /**
     * Prints the dealer's move header.
     */
    public void showDealerMoveStart() {
        System.out.println();
        System.out.println("Ход дилера");
        System.out.println("-------");
    }

    /**
     * Prints the card the dealer has just opened.
     *
     * @param card the card
     */
    public void showDealerOpensCard(Card card) {
        System.out.println("Дилер открывает карту " + card);
    }

    /**
     * Prints a result message.
     *
     * @param message result message
     */
    public void showResult(String message) {
        System.out.println(message);
    }

    /**
     * Prints the current score.
     *
     * @param playerWins how many rounds the player has won
     * @param dealerWins how many rounds the dealer has won
     */
    public void showScore(int playerWins, int dealerWins) {
        System.out.println("(ваш счет) " + playerWins + ":" + dealerWins + " (счет дилера)");
    }

    /**
     * make a HandView
     * @return the hand view used to print hands
     */
    public HandView getHandView() {
        return handView;
    }
}
