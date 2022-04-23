package com.tcg.action;

import com.tcg.Card;
import com.tcg.Target;

public class DoDamageAction implements Action {
    private Card card;
    private Target target;

    public DoDamageAction(Card card, Target target) {
        this.card = card;
        this.target = target;
    }

    @Override
    public void execute() {
        target.takeDamage(card.getManaCost());
    }

}
