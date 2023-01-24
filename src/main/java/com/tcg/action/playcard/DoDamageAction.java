package com.tcg.action.playcard;

import com.tcg.Player;
import com.tcg.Target;
import com.tcg.system.VictorySystem;

public class DoDamageAction extends PlayCardAction {

    private VictorySystem victorySystem;
    private Target target;

    public DoDamageAction(Player affectedPlayer, int cardIndex, VictorySystem victorySystem, Target target) {
        super(affectedPlayer, cardIndex);
        this.victorySystem = victorySystem;
        this.target = target;
    }

    @Override
    public void action() {
        target.takeDamage(card.getDamageValue());
        victorySystem.checkWinner();
    }

}
