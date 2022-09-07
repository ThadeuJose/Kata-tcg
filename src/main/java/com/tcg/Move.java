package com.tcg;

public class Move {

    private int cardIndex;
    private Type type;
    private Target target;
    private int activePlayerMinionIdx;
    private int nonActivePlayerMinionIdx;

    private Move(Builder builder) {
        cardIndex = builder.cardIndex;
        type = builder.type;
        target = builder.target;
        activePlayerMinionIdx = builder.activePlayerMinionIdx;
        nonActivePlayerMinionIdx = builder.nonActivePlayerMinionIdx;
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

    public int getActivePlayerMinionIdx() {
        return activePlayerMinionIdx;
    }

    public int getNonActivePlayerMinionIdx() {
        return nonActivePlayerMinionIdx;
    }

    public static class Builder {
        private int cardIndex;
        private Type type;
        private Target target;
        private int activePlayerMinionIdx;
        private int nonActivePlayerMinionIdx;

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

        public Builder setActivePlayerMinionIdx(int activePlayerMinionIdx) {
            this.activePlayerMinionIdx = activePlayerMinionIdx;
            return this;
        }

        public Builder setNonActivePlayerMinionIdx(int nonActivePlayerMinionIdx) {
            this.nonActivePlayerMinionIdx = nonActivePlayerMinionIdx;
            return this;
        }

        // TODO Make validate can only create a damage with target
        public Move build() {
            return new Move(this);
        }

    }

}
