package com.tcg;

public class Move {

    private int cardIndex;
    private Type type;
    private Target target;

    private Move(Builder builder) {
        cardIndex = builder.cardIndex;
        type = builder.type;
        target = builder.target;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Type getType() {
        return type;
    }

    public Target getTarget() {
        return target;
    }

    public static class Builder {
        private int cardIndex;
        private Type type;
        private Target target;

        public Builder setCardIndex(int cardIndex) {
            this.cardIndex = cardIndex;
            return this;
        }

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Builder setTarget(Target target) {
            this.target = target;
            return this;
        }

        // TODO Make validate can only create a damage with target
        public Move build() {
            return new Move(this);
        }

    }

}
