package com.tcg.action.playcard;

import com.tcg.Player;

public class CreateMinionAction extends PlayCardAction {

    public CreateMinionAction(Player affectedPlayer, int cardIndex) {
        super(affectedPlayer, cardIndex);
    }

    @Override
    public void action() {
        affectedPlayer.addMinionOnBoard(card.createMinion());
    }
}
