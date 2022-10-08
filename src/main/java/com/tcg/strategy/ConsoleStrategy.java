package com.tcg.strategy;

import com.tcg.Game;
import com.tcg.Interpreter;
import com.tcg.Player;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;

public class ConsoleStrategy implements Strategy {

    private PrintSystem printSystem;
    private InputSystem inputSystem;
    private Interpreter interpreter;

    public ConsoleStrategy(PrintSystem printSystem, InputSystem inputSystem) {
        this.printSystem = printSystem;
        this.inputSystem = inputSystem;
        interpreter = new Interpreter();

    }

    @Override
    public void play(Game game) {
        Player opponent = game.getNonActivePlayer();
        Player myself = game.getActivePlayer();
        printSystem.print("Opponent: " + opponent.getCurrentHealth() + " Life");
        printSystem.print("Opponent: " + opponent.getHandSize() + " cards in hand");
        printSystem.print("Opponent board:\n" + opponent.printBoardWithoutInformation());
        printSystem.print("Myself: " + myself.getCurrentHealth() + " Life");
        printSystem.print("Myself: " + myself.getCurrentMana() + " mana");
        printSystem.print("My board:\n" + myself.printBoardWithAllInformation());
        printSystem.print("Hand: \n" + myself.printHand());

        printSystem.print("Digit command: ");
        String command = inputSystem.getInput();
        printSystem.print(command);

        game.action(interpreter.createMove(command));

    }

}
