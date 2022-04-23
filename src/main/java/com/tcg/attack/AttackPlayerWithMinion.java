package com.tcg.attack;

import com.tcg.Minion;
import com.tcg.Player;

public class AttackPlayerWithMinion implements Attack {

    @Override
    public void execute(Player player, Minion minion) {
        player.takeDamage(minion.getPower());
    }

}
