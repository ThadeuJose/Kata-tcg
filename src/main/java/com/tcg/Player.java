package com.tcg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player implements Target, Combatant {
    private static final int MAX_HAND_SIZE = 5;
    private static final int MAX_HEALTH = 30;
    private static final int STARTER_MANA_SLOT = 0;
    private String name;
    private int health;
    private int manaSlot;
    private int mana;
    private List<Card> hand;
    protected Deck deck;
    private Board board;

    private Player(Builder builder) {
        this.name = builder.playerName;
        health = builder.health;
        mana = builder.mana;
        manaSlot = STARTER_MANA_SLOT;
        hand = builder.hand;
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

    public Player(String string, PassStrategy passStrategy) {
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

    public void spendMana(int cost) {
        this.mana -= cost;
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

    public Minion getMinion(int minionIdx) {
        return board.get(minionIdx);
    }

    public void awakeMinions() {
        board.awakeMinions();
    }

    public void cleanMinionsWith0Health() {
        board.cleanMinionsWith0Health();
    }

    public static Player createPlayerWithEmptyDeck() {
        return new Player.Builder().build();
    }

    public static Player createPlayerWithStandardDeck() {
        return new Player.Builder().setDeck(Deck.createStandardDeck()).build();
    }

    public static class Builder {

        private static final int STARTER_HEALTH = 30;
        private static final int STARTER_MANA = 0;

        private String playerName;
        private Deck deck;
        private int health;
        private int mana;
        private List<Card> hand;

        public Builder() {
            playerName = "Player";
            deck = Deck.createEmptyDeck();
            health = STARTER_HEALTH;
            mana = STARTER_MANA;
            hand = new ArrayList<>();
        }

        public Builder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder setDeck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setMana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder setCardsInHand(Card... cards) {
            this.hand = new ArrayList<>(Arrays.asList(cards));
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    @Override
    public void takeDamage(int damage) {
        health = getCurrentHealth() - damage;
    }

    public void heal(int value) {
        int newHealth = getCurrentHealth() + value;
        health = Math.min(newHealth, MAX_HEALTH);
    }

    @Override
    public int getAttackValue() {
        return 0;
    }

}
