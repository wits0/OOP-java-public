// CHECKSTYLE.OFF: PackageName
package ru.nsu.wits.task_1_1_2.models;
// CHECKSTYLE.ON: PackageName

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
     * @return Russian name of the suit
     */
    public String getRuName() {
        return ruName;
    }
}
