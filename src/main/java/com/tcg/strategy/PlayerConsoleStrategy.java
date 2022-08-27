package com.tcg.strategy;

import java.util.Scanner;

import com.tcg.Game;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.Type;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;

public class PlayerConsoleStrategy implements Strategy {

    private PrintSystem printSystem;
    private InputSystem inputSystem;

    public PlayerConsoleStrategy(PrintSystem printSystem, InputSystem inputSystem) {
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
        // My Board
        printSystem.print("Hand: \n" + myself.printHand());

        System.out.println("Digit command: ");
        String command = inputSystem.getInput();
        System.out.println(command);

        if (command.equals("D")) {
            Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                    .setTarget(game.getOppositionPlayerTarget())
                    .build();
            game.play(move);
        } else if (command.equals("P")) {
            game.pass();
        } else {
            game.endGame();
        }

    }

}
