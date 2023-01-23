package com.tcg.strategy;

import com.tcg.Move;
import com.tcg.strategy.boardstate.OpponentBoardstate;
import com.tcg.strategy.boardstate.PlayerBoardstate;

public interface Strategy {
    public Move play(OpponentBoardstate opponentBoardstate, PlayerBoardstate playerBoardstate);
}
