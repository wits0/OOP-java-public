package ru.nsu.wits.task_1_1_2.models;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank,Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString(){
        return rank.getNameCard() + " " + suit.getRuName() + " ("+ rank.getValueCard()+")";
    }
}
