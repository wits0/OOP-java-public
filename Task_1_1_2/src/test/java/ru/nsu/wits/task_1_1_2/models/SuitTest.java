package ru.nsu.wits.task_1_1_2.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuitTest {
    @Test
    void suitNamesAreReturned() {
        assertEquals("Пики", Suit.SPADES.getRuName());
        assertEquals("Черви", Suit.HEARTS.getRuName());
        assertEquals("Бубны", Suit.DIAMONDS.getRuName());
        assertEquals("Трефы", Suit.CLUBS.getRuName());
    }
}