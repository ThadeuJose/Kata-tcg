package com.tcg;

public class Card {
    private int manaCost;

    public Card(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Minion CreateMinion() {
        return new Minion(getManaCost());
    }

}
