package com.tcg.util;

import com.tcg.Game;
import com.tcg.strategy.ConsoleStrategy;
import com.tcg.system.PrintSystem;

public class TestPlayerConsoleStrategy extends ConsoleStrategy {

    public TestPlayerConsoleStrategy(PrintSystem printSystem) {
        super(printSystem, new NullInputSytem());
    }

    @Override
    public void play(Game game) {
        super.play(game);
        game.endGame();
    }

}
