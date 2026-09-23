package ru.nsu.wits.task_1_1_2.models;
/**
 * suit of our cards(naming)
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
     * @return hidden ruName
     *  func for getting private final String
     */
    public String getRuName() {
        return ruName;
    }
}
