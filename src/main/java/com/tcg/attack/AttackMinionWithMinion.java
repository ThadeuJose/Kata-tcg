package com.tcg.attack;

import com.tcg.Minion;
import com.tcg.Player;

public class AttackMinionWithMinion implements Attack {

    Player activePlayer;
    Minion enemyMinion;

    public AttackMinionWithMinion(Player activePlayer, Minion enemyMinion) {
        this.activePlayer = activePlayer;
        this.enemyMinion = enemyMinion;
    }

    @Override
    public void execute(Player nonActivePlayer, Minion alliedMinion) {
        enemyMinion.takeDamage(alliedMinion.getPower());
        alliedMinion.takeDamage(enemyMinion.getPower());

        activePlayer.cleanMinionsWith0Health();
        nonActivePlayer.cleanMinionsWith0Health();

    }

}
