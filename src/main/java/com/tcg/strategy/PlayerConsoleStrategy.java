package com.tcg.strategy;

import com.tcg.Game;
import com.tcg.Player;
import com.tcg.system.PrintSystem;

public class PlayerConsoleStrategy implements Strategy {

    private PrintSystem printSystem;

    public PlayerConsoleStrategy(PrintSystem printSystem) {
        this.printSystem = printSystem;
    }

    @Override
    public void play(Game game) {
        Player opponent = game.getNonActivePlayer();
        Player myself = game.getActivePlayer();
        printSystem.print("Opponent: " + opponent.getCurrentHealth() + " Life");
        printSystem.print("Opponent: " + opponent.getHandSize() + " cards in hand");
        printSystem.print("Myself: " + myself.getCurrentHealth() + " Life");
    }

}
