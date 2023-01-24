package com.tcg.action.playcard;

import com.tcg.Player;

public class HealingAction extends PlayCardAction {

    public HealingAction(Player affectedPlayer, int cardIndex) {
        super(affectedPlayer, cardIndex);
    }

    @Override
    public void action() {
        affectedPlayer.heal(card.getHealingValue());
    }

}
