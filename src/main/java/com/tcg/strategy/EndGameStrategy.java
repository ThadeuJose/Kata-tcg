package com.tcg.strategy;

import com.tcg.Move;
import com.tcg.Type;
import com.tcg.strategy.boardstate.OpponentBoardstate;
import com.tcg.strategy.boardstate.PlayerBoardstate;

public class EndGameStrategy implements Strategy {

    @Override
    public Move play(OpponentBoardstate opponentBoardstate, PlayerBoardstate playerBoardstate) {
        return new Move.Builder().setType(Type.AS_END).build();
    }
}
