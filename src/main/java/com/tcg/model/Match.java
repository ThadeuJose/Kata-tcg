package com.tcg.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcg.Player;

@Component
public class Match {
    private Player activePlayer;
    private Player nonActivePlayer;

    public Match(@Qualifier("player1") Player activePlayer, @Qualifier("player2") Player nonActivePlayer) {
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
