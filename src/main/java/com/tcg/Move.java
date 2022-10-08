package com.tcg;

import com.tcg.target.TargetType;
import com.tcg.target.TargetMinion;
import com.tcg.target.TargetPlayer;

public class Move {

    private int cardIndex;
    private Type type;

    private int activePlayerMinionIdx;
    private int nonActivePlayerMinionIdx;

    private TargetType targetType;

    private Move(Builder builder) {
        cardIndex = builder.cardIndex;
        type = builder.type;

        targetType = builder.targetType;

        activePlayerMinionIdx = builder.activePlayerMinionIdx;
        nonActivePlayerMinionIdx = builder.nonActivePlayerMinionIdx;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Type getType() {
        return type;
    }

    public int getActivePlayerMinionIdx() {
        return activePlayerMinionIdx;
    }

    public int getNonActivePlayerMinionIdx() {
        return nonActivePlayerMinionIdx;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public static class Builder {
        private int cardIndex;
        private Type type;
        private int activePlayerMinionIdx;
        private int nonActivePlayerMinionIdx;

        private TargetType targetType;

        public Builder setCardIndex(int cardIndex) {
            this.cardIndex = cardIndex;
            return this;
        }

        public Builder setType(Type type) {
            this.type = type;
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

        public Move endTurn() {
            this.type = Type.AS_END;
            return build();
        }

        public Move dealDamage(int cardIndex, TargetType target) {
            this.cardIndex = cardIndex;
            this.type = Type.AS_DAMAGE;
            this.targetType = target;
            return build();
        }

        public Move heal(int cardIndex) {
            this.cardIndex = cardIndex;
            this.type = Type.AS_HEALING;
            return build();
        }

        public Move draw(int cardIndex) {
            this.cardIndex = cardIndex;
            this.type = Type.AS_DRAW;
            return build();
        }

        public Move createMinion(int cardIndex) {
            this.cardIndex = cardIndex;
            this.type = Type.AS_MINION;
            return build();
        }

        // TODO Make validate can only create a damage with target
        public Move build() {
            return new Move(this);
        }

    }

    public static TargetMinion toMinion(int index) {
        return new TargetMinion(index);
    }

    public static TargetPlayer toPlayer() {
        return new TargetPlayer();
    }

}
