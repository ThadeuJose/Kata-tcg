package com.tcg;

public class Player {
    private int health;
    private int manaSlot;
    private final int starterHealth = 30;
    private final int starterManaSlot = 0;

    public Player() {
        health = starterHealth;
        manaSlot = starterManaSlot;
    }

    public int getCurrentHealth() {
        return health;
    }

    public Object getCurrentManaSlot() {
        return manaSlot;
    }
}
