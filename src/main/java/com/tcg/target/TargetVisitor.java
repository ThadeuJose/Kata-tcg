package com.tcg.target;

import com.tcg.Game;
import com.tcg.Target;

public class TargetVisitor {
    Game game;

    public TargetVisitor(Game game) {
        this.game = game;
    }

    public Target visit(TargetMinion target) {
        return game.getMinionTarget(target.getIndex());
    }

    public Target visit(TargetPlayer target) {
        return game.getOppositionPlayerTarget();
    }

}
