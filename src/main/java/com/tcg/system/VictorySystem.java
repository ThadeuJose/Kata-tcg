package com.tcg.system;

import com.tcg.Game;

public class VictorySystem {

    Game game;

    public VictorySystem(Game game) {
        this.game = game;
    }

    public void checkWinner() {
        if (game.getActivePlayer().getCurrentHealth() <= 0) {
            game.setWinner(game.getNonActivePlayer());
            game.setVictory();
        }

        if (game.getNonActivePlayer().getCurrentHealth() <= 0) {
            game.setWinner(game.getActivePlayer());
            game.setVictory();
        }
    }

}
