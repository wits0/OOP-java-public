package ru.nsu.wits.task_1_1_2.game;
import ru.nsu.wits.task_1_1_2.models.Dealer;
import ru.nsu.wits.task_1_1_2.models.Deck;
import ru.nsu.wits.task_1_1_2.models.Player;
import ru.nsu.wits.task_1_1_2.iostuff.View;

/**
 * Runs the blackjack game: creates rounds and keeps the score.
 */
public class Game {

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final View view;
    private final ScoreCounter scoreCounter = new ScoreCounter();

    /**
     * @param deck the deck to draw cards from
     * @param player the player
     * @param dealer the dealer
     * @param view the view to print messages
     */
    public Game(Deck deck, Player player, Dealer dealer, View view) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.view = view;
    }

    /**
     * Starts the game loop. Plays rounds until the program is stopped.
     */
    public void start() {
        view.showWelcome();
        int roundNumber = 1;
        while (true) {
            Round round = new Round(deck, player, dealer, view);
            RoundResult result = round.play(roundNumber);
            handleResult(result);
            resetHands();
            roundNumber++;
        }
    }

    private void handleResult(RoundResult result) {
        switch (result) {
            case PLAYER_WIN:
            case PLAYER_BLACKJACK:
                scoreCounter.addPlayerWin();
                view.showResult("Вы выиграли раунд!");
                break;
            case DEALER_WIN:
            case DEALER_BLACKJACK:
                scoreCounter.addDealerWin();
                view.showResult("Вы проиграли раунд.");
                break;
            case DRAW:
                view.showResult("Ничья!");
                break;
        }
        view.showScore(scoreCounter.getPlayerWins(), scoreCounter.getDealerWins());
    }

    private void resetHands() {
        player.getHand().clear();
        dealer.getHand().clear();
    }
}