package com.tcg.action;

import com.tcg.Card;
import com.tcg.Player;

public class DrawAction implements Action {

    private Card card;
    private Player affectedPlayer;

    public DrawAction(Card card, Player affectedPlayer) {
        this.card = card;
        this.affectedPlayer = affectedPlayer;
    }

    @Override
    public void execute() {
        for (int i = 0; i < card.quantityOfCardsToDraw(); i++) {
            if (affectedPlayer.getDeckSize() == 0) {
                affectedPlayer.takeDamage(1);
            } else {
                affectedPlayer.draw();
            }
        }
    }

}
