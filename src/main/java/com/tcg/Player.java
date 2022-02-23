package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int health;
    private int manaSlot;
    private int mana;
    private final int starterHealth = 30;
    private final int starterManaSlot = 0;
    private List<Card> hand;
    private Deck deck;

    public Player() {
        health = starterHealth;
        manaSlot = starterManaSlot;
        hand = new ArrayList<>();
        deck = new Deck();
    }

    public Player(Player player) {
        health = player.starterHealth;
        manaSlot = player.starterManaSlot;
        hand = new ArrayList<>(player.hand);
        deck = new Deck(player.deck);
    }

    public void initDeck() {
        deck.addCard(new Card(0));
        deck.addCard(new Card(0));
        deck.addCard(new Card(1));
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));
        deck.addCard(new Card(2));
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        deck.addCard(new Card(3));
        deck.addCard(new Card(3));
        deck.addCard(new Card(3));
        deck.addCard(new Card(4));
        deck.addCard(new Card(4));
        deck.addCard(new Card(4));
        deck.addCard(new Card(5));
        deck.addCard(new Card(5));
        deck.addCard(new Card(6));
        deck.addCard(new Card(6));
        deck.addCard(new Card(7));
        deck.addCard(new Card(8));
    }

    public int getCurrentHealth() {
        return health;
    }

    public int getCurrentManaSlot() {
        return manaSlot;
    }

    public int getHandSize() {
        return hand.size();
    }

    public int getDeckSize() {
        return deck.size();
    }

    public void draw() {
        Card card = deck.drawCard();
        hand.add(card);
    }

    public int getSlots() {
        return manaSlot;
    }

    public void addEmptySlot() {
        if (manaSlot < 10) {
            manaSlot++;
        }
    }

    public int getCurrentMana() {
        return mana;
    }

    public void refill() {
        mana = manaSlot;
    }
}
