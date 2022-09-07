package com.tcg.util;

import com.tcg.Game;
import com.tcg.Move;
import com.tcg.Type;
import com.tcg.strategy.Strategy;

public class TestPlayerDecorator implements Strategy {
    private Strategy strategy;

    public TestPlayerDecorator(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void play(Game game) {
        strategy.play(game);
        Move move = new Move.Builder().setType(Type.AS_END)
                .build();
        game.action(move);
    }

}
