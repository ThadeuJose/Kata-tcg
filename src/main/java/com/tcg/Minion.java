package com.tcg;

import java.util.StringJoiner;

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

    public Minion(Builder builder) {
        sleep = true;
        alreadyAttack = false;
        power = builder.power;
        health = builder.health;
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

    public void attack() {
        alreadyAttack = true;
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

    public static Minion buildDefaultMinion(int value) {
        Builder builder = new Builder();
        if (value > 0) {
            builder.setPower(value).setHealth(value);
        }
        return builder.build();
    }

    public static class Builder {
        private static final int MINIMUM_POWER = 0;
        private static final int MINIMUM_HEALTH = 1;
        private int power;
        private int health;

        public Builder() {
            power = MINIMUM_POWER;
            health = MINIMUM_HEALTH;
        }

        public Builder setPower(int value) {
            power = value;
            return this;
        }

        public Builder setHealth(int value) {
            health = value;
            return this;
        }

        private void validate() {
            StringJoiner sb = new StringJoiner("\n");

            if (power < 0) {
                sb.add("Minion shouldn't have less then 0 power");
            }

            if (health <= 0) {
                sb.add("Minion shouldn't have less then 1 health");
            }

            if (sb.length() > 0) {
                throw new IllegalStateException(sb.toString());
            }
        }

        public Minion build() {
            validate();
            return new Minion(this);
        }

    }

}
