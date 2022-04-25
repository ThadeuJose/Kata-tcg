package com.tcg.action;

import com.tcg.Card;
import com.tcg.Player;

public class HealingAction implements Action {

    private Card card;
    private Player affectedPlayer;

    public HealingAction(Card card, Player affectedPlayer) {
        this.card = card;
        this.affectedPlayer = affectedPlayer;
    }

    @Override
    public void execute() {
        affectedPlayer.heal(card.getHealingValue());
    }

}
