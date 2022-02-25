package com.tcg;

public class Game {
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;
    private Player activePlayer;
    private Player nonActivePlayer;

    public Game() {
        this(new Player(), new Player());
    }

    public Game(Player activePlayer, Player nonActivePlayer) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
    }

    public void init() {
        activePlayer.initDeck();
        nonActivePlayer.initDeck();
        for (int i = 0; i < MAX_HAND_ACTIVE_PLAYER; i++) {
            activePlayer.draw();
        }
        for (int i = 0; i < MAX_HAND_NON_ACTIVE_PLAYER; i++) {
            nonActivePlayer.draw();
        }

    }

    public Player getActivePlayer() {
        return new Player(activePlayer);
    }

    public Player getNonActivePlayer() {
        return new Player(nonActivePlayer);
    }

    public void startTurn() {
        activePlayer.addEmptySlot();
        activePlayer.refill();
        activePlayer.draw();
    }

    public void play(int cardIndex) {
        Card card = activePlayer.getCardFromHand(cardIndex);
        activePlayer.setMana(activePlayer.getCurrentMana() - card.getManaCost());
        nonActivePlayer.setHealth(nonActivePlayer.getCurrentHealth() - card.getManaCost());
    }

}
