package com.tcg;

public class Card {
    private int manaCost;
    private int damageValue;
    private int healingValue;
    private Minion minion;
    private int quantityOfCardsToDraw;

    private Card(Builder builder) {
        this.manaCost = builder.manaCost;
        this.damageValue = builder.damageValue;
        this.healingValue = builder.healingValue;
        this.minion = builder.minion;
        this.quantityOfCardsToDraw = builder.quantityOfCardsToDraw;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Minion createMinion() {
        return minion;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public int getHealingValue() {
        return healingValue;
    }

    public int quantityOfCardsToDraw() {
        return quantityOfCardsToDraw;
    }

    public static class Builder {
        private int manaCost;
        private int damageValue;
        private int healingValue;
        private Minion minion;
        private int quantityOfCardsToDraw;

        public Builder(int manaCost) {
            this.manaCost = manaCost;
            this.damageValue = manaCost;
            this.healingValue = manaCost;
            this.minion = new Minion(manaCost);
            this.quantityOfCardsToDraw = manaCost;
        }

        public Builder setDamage(int value) {
            this.damageValue = value;
            return this;
        }

        public Builder setHealing(int value) {
            this.healingValue = value;
            return this;
        }

        public Builder setMinion(Minion minion) {
            this.minion = minion;
            return this;
        }

        public Builder setCardDraw(int value) {
            this.quantityOfCardsToDraw = value;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }

}
