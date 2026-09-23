package ru.nsu.wits.task_1_1_2.game;

/**
 * Keeps the score of the game: how many rounds the player and the dealer have won.
 */
public class ScoreCounter {

    private int playerWins = 0;
    private int dealerWins = 0;

    /**
     * Adds one win to the player.
     */
    public void addPlayerWin() {
        playerWins++;
    }

    /**
     * Adds one win to the dealer.
     */
    public void addDealerWin() {
        dealerWins++;
    }

    /**
     * @return how many rounds the player has won
     */
    public int getPlayerWins() {
        return playerWins;
    }

    /**
     * @return how many rounds the dealer has won
     */
    public int getDealerWins() {
        return dealerWins;
    }
}
