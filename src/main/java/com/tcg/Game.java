package com.tcg;

public class Game {
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;
    private Player activePlayer;
    private Player nonActivePlayer;

    public Game() {
        activePlayer = new Player();
        nonActivePlayer = new Player();
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
    }

}
