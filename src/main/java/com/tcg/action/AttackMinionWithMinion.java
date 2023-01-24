package com.tcg.action;

import com.tcg.Minion;
import com.tcg.Player;

public class AttackMinionWithMinion implements Action {

    private Player activePlayer;
    private Player nonActivePlayer;
    private int activePlayerMinionIdx;
    private int nonActivePlayerMinionIdx;

    public AttackMinionWithMinion(Player activePlayer, Player nonActivePlayer, int activePlayerMinionIdx,
            int nonActivePlayerMinionIdx) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
        this.activePlayerMinionIdx = activePlayerMinionIdx;
        this.nonActivePlayerMinionIdx = nonActivePlayerMinionIdx;
    }

    @Override
    public void execute() {
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        Minion enemyMinion = nonActivePlayer.getMinion(nonActivePlayerMinionIdx);

        enemyMinion.takeDamage(alliedMinion.getAttackValue());
        alliedMinion.takeDamage(enemyMinion.getAttackValue());

        activePlayer.cleanMinionsWith0Health();
        nonActivePlayer.cleanMinionsWith0Health();
    }

}
