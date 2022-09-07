package com.tcg.strategy;

import com.tcg.Game;
import com.tcg.Move;
import com.tcg.Type;

public class EndGameStrategy implements Strategy {

    @Override
    public void play(Game game) {
        Move move = new Move.Builder().setType(Type.AS_END)
                .build();
        game.action(move);
    }
}
