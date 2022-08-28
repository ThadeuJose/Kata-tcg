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
        return DeckFactory.createStandardDeck();
    }

    public static class Builder {

        private ArrayList<Card> list;

        public Builder() {
            list = new ArrayList<>();
        }

        public Builder addCard(int quantity, int manaCost) {
            for (int i = 0; i < quantity; i++) {
                list.add(new Card.Builder(manaCost).build());
            }
            return this;
        }

        public Deck build() {
            return new Deck(this);
        }
    }

}
