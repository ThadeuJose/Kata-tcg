package com.tcg.strategy.boardstate;

import com.tcg.Board;
import com.tcg.model.Hand;

public class PlayerBoardstate {
    private int currentHealth;
    private int currentMana;
    private Board board;
    private Hand hand;

    public PlayerBoardstate(int currentHealth, int currentMana, Board board, Hand hand) {
        this.currentHealth = currentHealth;
        this.currentMana = currentMana;
        this.board = board;
        this.hand = hand;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public Board getBoard() {
        return board;
    }

    public Hand getHand() {
        return hand;
    }

}
