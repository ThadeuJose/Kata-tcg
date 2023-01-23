package com.tcg.model;

import java.util.ArrayList;
import java.util.List;

import com.tcg.Card;

public class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public String printHand() {
        String result = "";
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            result += "[" + i + "] " + card.toString() + "\n";
        }
        return result;
    }
}
