package com.tcg.strategy;

import com.tcg.Interpreter;
import com.tcg.Move;
import com.tcg.strategy.boardstate.OpponentBoardstate;
import com.tcg.strategy.boardstate.PlayerBoardstate;
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
    public Move play(OpponentBoardstate opponentBoardstate, PlayerBoardstate playerBoardstate) {
        printSystem.print("Opponent: " + opponentBoardstate.getCurrentHealth() + " Life");
        printSystem.print("Opponent: " + opponentBoardstate.getHandSize() + " cards in hand");
        printSystem.print("Opponent board:\n" + opponentBoardstate.getBoard().printWithoutInformation());

        printSystem.print("Myself: " + playerBoardstate.getCurrentHealth() + " Life");
        printSystem.print("Myself: " + playerBoardstate.getCurrentMana() + " mana");
        printSystem.print("My board:\n" + playerBoardstate.getBoard().printAllInformation());
        printSystem.print("Hand: \n" + playerBoardstate.getHand().printHand());

        printSystem.print("Digit command: ");
        String command = inputSystem.getInput();
        printSystem.print(command);
        return interpreter.createMove(command);
    }

}
