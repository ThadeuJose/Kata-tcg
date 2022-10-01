package com.tcg;

public class Interpreter {

    public Move createMove(String command) {
        Move move;
        if (command.equals("D")) {
            move = new Move.Builder().dealDamage(0, Move.toPlayer());

        } else if (command.equals("P")) {
            move = new Move.Builder().setType(Type.AS_PASS)
                    .build();

        } else {
            move = new Move.Builder().setType(Type.AS_END)
                    .build();
        }
        return move;
    }

}
