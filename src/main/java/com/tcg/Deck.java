package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> list;

    private Deck(Builder builder) {
        this.list = builder.list;
    }

    public Deck(Deck deck) {
        this.list = deck.list;
    }

    public void addCard(Card card) {
        list.add(card);
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(list);
    }

    public Card drawCard() {
        Card c = list.get(0);
        list.remove(0);
        return c;
    }

    public int size() {
        return list.size();
    }

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

    public static class Builder {

        private ArrayList<Card> list;

        public Builder() {
            list = new ArrayList<>();
        }

        public Builder addCard(int quantity, int manaCost) {
            for (int i = 0; i < quantity; i++) {
                list.add(new Card(manaCost));
            }
            return this;
        }

        public Deck build() {
            return new Deck(this);
        }
    }

}
