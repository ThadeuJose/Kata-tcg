package com.tcg;

public class CantAttackWithMinion extends RuntimeException {

    public CantAttackWithMinion(String errorMessage) {
        super(errorMessage);
    }

}
