package com.tcg.strategy;

import com.tcg.Game;

public class EndGameStrategy implements Strategy {

    @Override
    public void play(Game game) {
        game.endGame();
    }
}
