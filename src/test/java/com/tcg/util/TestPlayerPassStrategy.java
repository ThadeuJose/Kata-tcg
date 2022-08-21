package com.tcg.util;

import com.tcg.Game;
import com.tcg.strategy.PassStrategy;

public class TestPlayerPassStrategy extends PassStrategy {

    public TestPlayerPassStrategy() {
    }

    @Override
    public void play(Game game) {
        super.play(game);
        game.endGame();
    }

}
