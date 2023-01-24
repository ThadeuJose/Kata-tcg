package com.tcg.action.playcard;

import com.tcg.Player;
import com.tcg.system.VictorySystem;

public class DrawAction extends PlayCardAction {

    private VictorySystem victorySystem;

    public DrawAction(Player affectedPlayer, int cardIndex, VictorySystem victorySystem) {
        super(affectedPlayer, cardIndex);
        this.victorySystem = victorySystem;
    }

    @Override
    public void action() {
        for (int i = 0; i < card.quantityOfCardsToDraw(); i++) {
            if (affectedPlayer.getDeckSize() == 0) {
                affectedPlayer.takeDamage(1);
            } else {
                affectedPlayer.draw();
            }
        }
        victorySystem.checkWinner();
    }

}
