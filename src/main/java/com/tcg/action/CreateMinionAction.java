package com.tcg.action;

import com.tcg.Card;
import com.tcg.Player;

public class CreateMinionAction implements Action {

    private Card card;
    private Player affectedPlayer;

    public CreateMinionAction(Card card, Player affectedPlayer) {
        this.card = card;
        this.affectedPlayer = affectedPlayer;
    }

    @Override
    public void execute() {
        affectedPlayer.addMinionOnBoard(card.createMinion());
    }

}
