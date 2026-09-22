package ru.nsu.wits.task_1_1_2.blackjack;
import ru.nsu.wits.task_1_1_2.game.Move;
import java.util.Scanner;

/**
 * User = player.
 */
public class Player extends PlayerAbstract {

    private final Scanner scanner;

    /**
     * @param name name of the player
     * @param scanner scanner to read user input
     */
    public Player(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public Move decideMove() {
        System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться.");
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("1")) {
                return Move.HIT;
            }
            if (line.equals("0")) {
                return Move.STAND;
            }
            System.out.println("Введите 1 или 0.");
        }
    }
}