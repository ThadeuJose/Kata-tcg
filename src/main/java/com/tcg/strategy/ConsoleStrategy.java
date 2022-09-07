package com.tcg.strategy;

import com.tcg.Game;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.Type;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;

public class ConsoleStrategy implements Strategy {

    private PrintSystem printSystem;
    private InputSystem inputSystem;

    public ConsoleStrategy(PrintSystem printSystem, InputSystem inputSystem) {
        this.printSystem = printSystem;
        this.inputSystem = inputSystem;
    }

    @Override
    public void play(Game game) {
        Player opponent = game.getNonActivePlayer();
        Player myself = game.getActivePlayer();
        printSystem.print("Opponent: " + opponent.getCurrentHealth() + " Life");
        printSystem.print("Opponent: " + opponent.getHandSize() + " cards in hand");
        // Opponent Board
        printSystem.print("Myself: " + myself.getCurrentHealth() + " Life");
        printSystem.print("Myself: " + myself.getCurrentMana() + " mana");
        // My Board
        printSystem.print("Hand: \n" + myself.printHand());

        printSystem.print("Digit command: ");
        String command = inputSystem.getInput();
        printSystem.print(command);

        if (command.equals("D")) {
            Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                    .setTarget(game.getOppositionPlayerTarget())
                    .build();
            game.action(move);
        } else if (command.equals("P")) {
            Move move = new Move.Builder().setType(Type.AS_PASS)
                    .build();
            game.action(move);
        } else {
            Move move = new Move.Builder().setType(Type.AS_END)
                    .build();
            game.action(move);
        }

    }

}
