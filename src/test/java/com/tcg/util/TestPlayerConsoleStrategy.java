package com.tcg.util;

import com.tcg.Game;
import com.tcg.strategy.PlayerConsoleStrategy;
import com.tcg.system.PrintSystem;

public class TestPlayerConsoleStrategy extends PlayerConsoleStrategy {

    public TestPlayerConsoleStrategy(PrintSystem printSystem) {
        super(printSystem, new NullInputSytem());
    }

    @Override
    public void play(Game game) {
        super.play(game);
        game.endGame();
    }

}
