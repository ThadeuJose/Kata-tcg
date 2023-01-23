package com.tcg.strategy.boardstate;

import com.tcg.Board;

public class OpponentBoardstate {
    private int currentHealth;
    private int handSize;
    private Board board;

    public OpponentBoardstate(int currentHealth, int handSize, Board board) {
        this.currentHealth = currentHealth;
        this.handSize = handSize;
        this.board = board;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getHandSize() {
        return handSize;
    }

    public Board getBoard() {
        return board;
    }

}
