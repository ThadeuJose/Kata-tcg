package com.tcg.model.minion;

import com.tcg.Minion;

public class MinionFactory {
    public static Minion createMinion(int power, int health) {
        return new Minion(power, health);
    }
}
