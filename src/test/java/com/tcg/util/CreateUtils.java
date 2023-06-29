package com.tcg.util;

import com.tcg.Deck;
import com.tcg.Game;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.model.ManaRefillService;
import com.tcg.model.Match;
import com.tcg.model.state.StateMachine;
import com.tcg.system.PrintSystem;

public class CreateUtils {

    public static Game createGame() {
        Player player1 = aPlayer().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build();
        Player player2 = aPlayer().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        TestPrintSystem testPrintSystem = new TestPrintSystem();
        return createGame(testPrintSystem, player1, player2);
    }

    public static Game createGame(ManaRefillService manaRefillService, Player activePlayer, Player nonActivePlayer) {
        TestPrintSystem testPrintSystem = new TestPrintSystem();
        return createGame(manaRefillService, testPrintSystem, activePlayer, nonActivePlayer);
    }

    public static Game createGame(Player activePlayer, Player nonActivePlayer) {
        TestPrintSystem testPrintSystem = new TestPrintSystem();
        return createGame(testPrintSystem, activePlayer, nonActivePlayer);
    }

    public static Game createGame(PrintSystem printSystem, Player player1, Player player2) {
        return createGame(new ManaRefillService(), printSystem, player1, player2);
    }

    public static Game createGame(ManaRefillService manaRefillService, PrintSystem printSystem, Player player1,
            Player player2) {
        Match match = new Match(player1, player2);
        return new Game(new StateMachine(), manaRefillService, printSystem, match);
    }

    public static Player.Builder aPlayer() {
        return new Player.Builder();
    }

    public static Move.Builder aMove() {
        return new Move.Builder();
    }

}
