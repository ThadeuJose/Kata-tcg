package com.tcg;

public class CantAffordCardException extends RuntimeException {

    public CantAffordCardException(String errorMessage) {
        super(errorMessage);
    }

}
