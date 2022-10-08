package com.tcg;

public class Interpreter {

    public Move createMove(String command) {
        if (command.startsWith("D")) {
            return new Move.Builder().dealDamage(getIndex(command), Move.toPlayer());
        }
        if (command.startsWith("H")) {
            return new Move.Builder().heal(getIndex(command));
        }
        if (command.startsWith("R")) {
            return new Move.Builder().draw(getIndex(command));
        }
        if (command.startsWith("C")) {
            return new Move.Builder().createMinion(getIndex(command));
        }
        if (command.equals("P"))
            return new Move.Builder().setType(Type.AS_PASS).build();
        return new Move.Builder().endTurn();
    }

    private int getIndex(String command) {
        if (command.length() == 2) {
            return Character.getNumericValue(command.charAt(1));
        }
        return 0;
    }

}
