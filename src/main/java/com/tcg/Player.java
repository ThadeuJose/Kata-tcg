package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int MAX_HAND_SIZE = 5;
    private static final int MAX_HEALTH = 30;
    private String name;
    private int health;
    private int manaSlot;
    private int mana;
    private final int starterHealth = 30;
    private final int starterManaSlot = 0;
    private List<Card> hand;
    protected Deck deck;
    private Board board;

    private Player(Builder builder) {
        this.name = builder.playerName;
        health = starterHealth;
        manaSlot = starterManaSlot;
        hand = new ArrayList<>();
        this.deck = builder.deck;
        this.board = new Board();
    }

    public Player(Player player) {
        name = player.name;
        health = player.health;
        manaSlot = player.manaSlot;
        mana = player.mana;
        hand = new ArrayList<>(player.hand);
        deck = new Deck(player.deck);
        board = player.board;
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
        if (hand.size() < MAX_HAND_SIZE) {
            hand.add(card);
        }
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

    public Card getCardFromHand(int cardIndex) {
        Card card = hand.get(cardIndex);
        hand.remove(cardIndex);
        return card;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, MAX_HEALTH);
    }

    public void setManaSlot(int manaSlot) {
        this.manaSlot = manaSlot;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public int getBoardSize() {
        return board.size();
    }

    public void addMinionOnBoard(Minion minion) {
        board.add(minion);
    }

    public static Player createPlayerWithEmptyDeck() {
        return new Player.Builder().build();
    }

    public static Player createPlayerWithStandardDeck() {
        return new Player.Builder().setDeck(Deck.createStandardDeck()).build();
    }

    public static class Builder {

        private String playerName;
        private Deck deck;

        public Builder() {
            playerName = "Player";
            deck = Deck.createEmptyDeck();
        }

        public Builder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder setDeck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

}
