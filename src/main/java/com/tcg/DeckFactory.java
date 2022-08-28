package com.tcg;

public class DeckFactory {

    public static Deck createStandardDeck() {
        return new Deck.Builder()
                .addCard(2, 0)
                .addCard(2, 1)
                .addCard(3, 2)
                .addCard(4, 3)
                .addCard(3, 4)
                .addCard(2, 5)
                .addCard(2, 6)
                .addCard(1, 7)
                .addCard(1, 8)
                .build();
    }

    public static Deck createEmptyDeck() {
        return new Deck.Builder().build();
    }

}
