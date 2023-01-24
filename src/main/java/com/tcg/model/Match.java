package com.tcg.model;

import com.tcg.Player;

public class Match {
    private Player activePlayer;
    private Player nonActivePlayer;

    public Match(Player activePlayer, Player nonActivePlayer) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getNonActivePlayer() {
        return nonActivePlayer;
    }

    public void changeActivePlayer() {
        Player temp = activePlayer;
        activePlayer = nonActivePlayer;
        nonActivePlayer = temp;
    }

}
