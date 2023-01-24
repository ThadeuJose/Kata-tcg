package com.tcg.action;

import com.tcg.Minion;
import com.tcg.Player;
import com.tcg.system.VictorySystem;

public class AttackPlayerWithMinionAction implements Action {

    private Player activePlayer;
    private Player nonActivePlayer;
    private int activePlayerMinionIdx;
    private VictorySystem victorySystem;

    public AttackPlayerWithMinionAction(Player activePlayer, Player nonActivePlayer, int activePlayerMinionIdx,
            VictorySystem victorySystem) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
        this.activePlayerMinionIdx = activePlayerMinionIdx;
        this.victorySystem = victorySystem;
    }

    @Override
    public void execute() {
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        nonActivePlayer.takeDamage(alliedMinion.getAttackValue());

        victorySystem.checkWinner();
    }

}
