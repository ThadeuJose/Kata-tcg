package com.tcg;

public class Minion {

    private int power;
    private int health;
    private boolean sleep;
    private boolean alreadyAttack;

    public Minion(int manaCost) {
        sleep = true;
        alreadyAttack = false;
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

    public void awake() {
        sleep = false;
    }

    public boolean isAwake() {
        return !sleep;
    }

    public boolean canAttack() {
        return !alreadyAttack;
    }

    public boolean attack() {
        return alreadyAttack = true;
    }

    public void takeDamage(int damage) {
        health = health - damage;
    }

    public void validateIfCanAttack() {
        if (!isAwake()) {
            throw new CantAttackWithMinion("Cant attack with a minion in the same turn is play");
        }

        if (!canAttack()) {
            throw new CantAttackWithMinion("Cant attack with a minion more then once");
        } else {
            attack();
        }

    }

}
