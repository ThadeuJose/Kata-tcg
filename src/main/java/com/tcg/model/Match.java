package com.tcg.model;

import java.util.Optional;

import com.tcg.Player;

public class Match {
    private Player activePlayer;
    private Player nonActivePlayer;
    private Player winner;

    public Match(Player activePlayer, Player nonActivePlayer) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
        winner = null;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getNonActivePlayer() {
        return nonActivePlayer;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void changeActivePlayer() {
        Player temp = activePlayer;
        activePlayer = nonActivePlayer;
        nonActivePlayer = temp;
    }

}
