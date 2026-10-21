package ru.nsu.wits.blackjacklab.iostuff;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.wits.blackjacklab.models.Card;
import ru.nsu.wits.blackjacklab.models.Rank;
import ru.nsu.wits.blackjacklab.models.Suit;

/**
 * Test of View.
 */
class ViewTest {
    private PrintStream original;
    private ByteArrayOutputStream out;

    @BeforeEach
    void capture() {
        original = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restore() {
        System.setOut(original);
    }

    @Test
    void messagesArePrinted() {
        View view = new View();
        view.showWelcome();
        view.showRoundStart(3);
        view.showPlayerMoveStart();
        view.showPlayerTakesCard(new Card(Rank.ACE, Suit.SPADES));
        view.showDealerMoveStart();
        view.showDealerOpensCard(new Card(Rank.TEN, Suit.HEARTS));
        view.showResult("Результат");
        view.showScore(2, 4);

        String s = out.toString();
        assertTrue(s.contains("Добро пожаловать в Блэкджек!"));
        assertTrue(s.contains("Раунд 3"));
        assertTrue(s.contains("Ваш ход"));
        assertTrue(s.contains("Вы открыли карту Туз Пики (11)"));
        assertTrue(s.contains("Ход дилера"));
        assertTrue(s.contains("Дилер открывает карту Десятка Черви (10)"));
        assertTrue(s.contains("Результат"));
        assertTrue(s.contains("(ваш счет) 2:4 (счет дилера)"));
    }

    @Test
    void getHandViewReturnsUsableView() {
        assertNotNull(new View().getHandView());
    }
}