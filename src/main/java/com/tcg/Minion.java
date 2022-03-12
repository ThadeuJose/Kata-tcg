package com.tcg;

public class Minion {

    private int power;
    private int health;
    private boolean sleep;

    public Minion(int manaCost) {
        sleep = true;
        if (manaCost > 0) {
            power = manaCost;
            health = manaCost;
        } else {
            power = 0;
            health = 1;
        }
    }

    public Minion(Minion minion) {
        power = minion.power;
        health = minion.health;
        sleep = minion.sleep;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public void awake() {
        sleep = false;
    }

    public boolean isAwake() {
        return !sleep;
    }

}
