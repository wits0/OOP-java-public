package ru.nsu.wits.blackjacklab.models;

/**
 * Card ranks and their blackjack values.
 */
public enum Rank {
    TWO(2, "Двойка"),
    THREE(3, "Тройка"),
    FOUR(4, "Четверка"),
    FIVE(5, "Пятерка"),
    SIX(6, "Шестерка"),
    SEVEN(7, "Семерка"),
    EIGHT(8, "Восьмерка"),
    NINE(9, "Девятка"),
    TEN(10, "Десятка"),
    JACK(10, "Валет"),
    QUEEN(10, "Дама"),
    KING(10, "Король"),
    ACE(11, "Туз");

    private final Integer valueCard;
    private final String nameCard;

    Rank(Integer valueCard, String nameCard) {
        this.valueCard = valueCard;
        this.nameCard = nameCard;
    }

    /**
     * Value of card.
     *
     * @return card value
     */
    public Integer getValueCard() {
        return valueCard;
    }

    /**
     * Name of card.
     *
     * @return card name
     */
    public String getNameCard() {
        return nameCard;
    }
}
