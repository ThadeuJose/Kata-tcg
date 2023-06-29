package com.tcg.model.match;

import com.tcg.Player;

public class Players {
    private Player activePlayer;
    private Player nonActivePlayer;

    public Players(Player activePlayer, Player nonActivePlayer) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getNonActivePlayer() {
        return nonActivePlayer;
    }

}
