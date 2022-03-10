package com.tcg;

public class Minion {

    private int power;
    private int health;

    public Minion(int manaCost) {
        if (manaCost > 0) {
            power = manaCost;
            health = manaCost;
        } else {
            power = 0;
            health = 1;
        }
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

}
