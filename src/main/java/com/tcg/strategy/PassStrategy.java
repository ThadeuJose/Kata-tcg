package com.tcg.strategy;

import com.tcg.Move;
import com.tcg.strategy.boardstate.OpponentBoardstate;
import com.tcg.strategy.boardstate.PlayerBoardstate;

public class PassStrategy implements Strategy {

    @Override
    public Move play(OpponentBoardstate opponentBoardstate, PlayerBoardstate playerBoardstate) {
        return new Move.Builder().pass();
    }

}
