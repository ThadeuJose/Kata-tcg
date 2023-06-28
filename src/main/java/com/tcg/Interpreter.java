package com.tcg;

public class Interpreter {

    public Move createMove(String command) {
        if (command.startsWith("d")) {
            return new Move.Builder().dealDamageToPlayer(getIndex(command));
        }
        if (command.startsWith("h")) {
            return new Move.Builder().heal(getIndex(command));
        }
        if (command.startsWith("r")) {
            return new Move.Builder().draw(getIndex(command));
        }
        if (command.startsWith("c")) {
            return new Move.Builder().createMinion(getIndex(command));
        }
        if (command.equals("p"))
            return new Move.Builder().pass();
        return new Move.Builder().endTurn();
    }

    private int getIndex(String command) {
        int defaultIndex = 0;
        if (command.length() == 3) {
            defaultIndex = Character.getNumericValue(command.charAt(2));
        }
        return defaultIndex;
    }

}
