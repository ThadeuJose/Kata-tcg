package com.tcg;

public class Interpreter {

    public Move createMove(String command, Target oppositionPlayerTarget) {
        Move move;
        if (command.equals("D")) {
            move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                    .setTarget(oppositionPlayerTarget)
                    .build();

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
