package com.tcg.model.move;

import com.tcg.Move;

public class MoveFactory {
    public static Move createPassMove() {
        return new Move.Builder().pass();
    }

    public static Move createEndMove() {
        return new Move.Builder().endTurn();
    }
}
