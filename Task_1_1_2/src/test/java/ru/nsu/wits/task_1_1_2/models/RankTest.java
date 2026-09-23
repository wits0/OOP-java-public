package ru.nsu.wits.task_1_1_2.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test void everyRankHasExpectedValueAndName() {
        assertEquals(2, Rank.TWO.getValueCard());
        assertEquals("Двойка", Rank.TWO.getNameCard());
        assertEquals(3, Rank.THREE.getValueCard());
        assertEquals("Тройка", Rank.THREE.getNameCard());
        assertEquals(4, Rank.FOUR.getValueCard());
        assertEquals("Четверка", Rank.FOUR.getNameCard());
        assertEquals(5, Rank.FIVE.getValueCard());
        assertEquals("Пятерка", Rank.FIVE.getNameCard());
        assertEquals(6, Rank.SIX.getValueCard());
        assertEquals("Шестерка", Rank.SIX.getNameCard());
        assertEquals(7, Rank.SEVEN.getValueCard());
        assertEquals("Семерка", Rank.SEVEN.getNameCard());
        assertEquals(8, Rank.EIGHT.getValueCard());
        assertEquals("Восьмерка", Rank.EIGHT.getNameCard());
        assertEquals(9, Rank.NINE.getValueCard());
        assertEquals("Девятка", Rank.NINE.getNameCard());
        assertEquals(10, Rank.TEN.getValueCard());
        assertEquals("Десятка", Rank.TEN.getNameCard());
        assertEquals(10, Rank.JACK.getValueCard());
        assertEquals("Валет", Rank.JACK.getNameCard());
        assertEquals(10, Rank.QUEEN.getValueCard());
        assertEquals("Дама", Rank.QUEEN.getNameCard());
        assertEquals(10, Rank.KING.getValueCard());
        assertEquals("Король", Rank.KING.getNameCard());
        assertEquals(11, Rank.ACE.getValueCard());
        assertEquals("Туз", Rank.ACE.getNameCard());
    }
}