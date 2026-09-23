package ru.nsu.wits.task_1_1_2.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import ru.nsu.wits.task_1_1_2.game.Move;

class PlayerTest {
    @Test
    void input1MeansHit() {
        Player player = new Player("Игрок", new Scanner("1\n"));
        assertEquals(Move.HIT, player.decideMove());
    }

    @Test
    void input0MeansStand() {
        Player player = new Player("Игрок", new Scanner("0\n"));
        assertEquals(Move.STAND, player.decideMove());
    }

    @Test
    void invalidInputIsRejectedUntilValidInput() {
        Player player = new Player("Игрок", new Scanner("abc\n2\n1\n"));
        assertEquals(Move.HIT, player.decideMove());
    }
}