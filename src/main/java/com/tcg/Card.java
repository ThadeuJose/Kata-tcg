package com.tcg;

public class Card {
    private int manaCost;
    private int damageValue;
    private int healingValue;
    private Minion minion;
    private int quantityOfCardsToDraw;

    public Card(int manaCost, int damageValue, int healingValue, Minion minion, int quantityOfCardsToDraw) {
        this.manaCost = manaCost;
        this.damageValue = damageValue;
        this.healingValue = healingValue;
        this.minion = minion;
        this.quantityOfCardsToDraw = quantityOfCardsToDraw;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + damageValue;
        result = prime * result + healingValue;
        result = prime * result + manaCost;
        result = prime * result + ((minion == null) ? 0 : minion.hashCode());
        result = prime * result + quantityOfCardsToDraw;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (damageValue != other.damageValue)
            return false;
        if (healingValue != other.healingValue)
            return false;
        if (manaCost != other.manaCost)
            return false;
        if (minion == null) {
            if (other.minion != null)
                return false;
        } else if (!minion.equals(other.minion))
            return false;
        if (quantityOfCardsToDraw != other.quantityOfCardsToDraw)
            return false;
        return true;
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
