package com.tcg;

public class InvalidPlayException extends RuntimeException {

    public InvalidPlayException(String errorMessage) {
        super(errorMessage);
    }

}
