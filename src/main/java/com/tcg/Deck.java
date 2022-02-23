package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> list;

    public Deck() {
        this.list = new ArrayList<>();
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

}
