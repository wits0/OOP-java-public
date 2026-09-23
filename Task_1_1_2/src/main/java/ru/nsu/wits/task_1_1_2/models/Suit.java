package ru.nsu.wits.task_1_1_2.models;

/**
 * Suits of playing cards.
 */
public enum Suit {
    SPADES("Пики"),
    HEARTS("Черви"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы");

    private final String ruName;

    Suit(String ruName) {
        this.ruName = ruName;
    }

    /**
     * getName of suit.
     *
     * @return Russian name of the suit
     */
    public String getRuName() {
        return ruName;
    }
}
