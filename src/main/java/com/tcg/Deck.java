package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> list;

    public Deck() {
        this.list = new ArrayList<>();
    }

    public void addCard(Card card) {
        list.add(card);
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(list);
    }

}
