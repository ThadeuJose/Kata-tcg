package com.tcg;

public class Move {

    protected Type type;

    private int cardIndex;

    private int activePlayerMinionIdx;
    private int nonActivePlayerMinionIdx;

    private Move(Builder builder) {
        cardIndex = builder.cardIndex;
        type = builder.type;

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

    public static class Builder {
        private Type type;
        private int cardIndex;
        private int activePlayerMinionIdx;
        private int nonActivePlayerMinionIdx;

        public Move endTurn() {
            this.type = Type.AS_END;
            return build();
        }

        public Move pass() {
            this.type = Type.AS_PASS;
            return build();
        }

        public Move heal(int cardIndex) {
            this.type = Type.AS_HEALING;
            this.cardIndex = cardIndex;
            return build();
        }

        public Move draw(int cardIndex) {
            this.type = Type.AS_DRAW;
            this.cardIndex = cardIndex;
            return build();
        }

        public Move createMinion(int cardIndex) {
            this.type = Type.AS_MINION;
            this.cardIndex = cardIndex;
            return build();
        }

        public Move attackMinionWithMinion(int activePlayerMinionIdx, int nonActivePlayerMinionIdx) {
            this.type = Type.AS_MINION_ATTACK_MINION;
            this.activePlayerMinionIdx = activePlayerMinionIdx;
            this.nonActivePlayerMinionIdx = nonActivePlayerMinionIdx;
            return build();
        }

        public Move attackPlayerWithMinion(int activePlayerMinionIdx) {
            this.type = Type.AS_MINION_ATTACK_PLAYER;
            this.activePlayerMinionIdx = activePlayerMinionIdx;
            return build();
        }

        public Move dealDamageToPlayer(int cardIndex) {
            this.type = Type.AS_DAMAGE_PLAYER;
            this.cardIndex = cardIndex;
            return build();
        }

        public Move dealDamageToMinion(int cardIndex, int nonActivePlayerMinionIdx) {
            this.type = Type.AS_DAMAGE_MINION;
            this.cardIndex = cardIndex;
            this.nonActivePlayerMinionIdx = nonActivePlayerMinionIdx;
            return build();
        }

        public Move build() {
            return new Move(this);
        }

    }

}
