package com.tcg.util;

import com.tcg.Deck;
import com.tcg.Game;
import com.tcg.Player;
import com.tcg.Player.Builder;
import com.tcg.system.PrintSystem;

public class CreateUtils {

    public static Game createGame() {
        Player player1 = aPlayer().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build();
        Player player2 = aPlayer().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        TestPrintSystem testPrintSystem = new TestPrintSystem();
        return createGame(testPrintSystem, player1, player2);
    }

    public static Game createGame(Player activePlayer, Player nonActivePlayer) {
        TestPrintSystem testPrintSystem = new TestPrintSystem();
        return createGame(testPrintSystem, activePlayer, nonActivePlayer);
    }

    public static Game createGame(PrintSystem printSystem, Player player1, Player player2) {
        return new Game(printSystem, player1, player2);
    }

    public static Builder aPlayer() {
        return new Player.Builder();
    }

}
