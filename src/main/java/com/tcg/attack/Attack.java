package com.tcg.attack;

import com.tcg.Minion;
import com.tcg.Player;

public interface Attack {
    public void execute(Player nonActivePlayer, Minion alliedMinion);
}
