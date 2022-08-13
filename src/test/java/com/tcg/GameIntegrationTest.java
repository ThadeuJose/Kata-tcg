package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.strategy.EndGameStrategy;
import com.tcg.util.TestPrintSystem;

public class GameIntegrationTest {

    @Test
    public void shouldStartGame() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createGame(printSystem);
        game.init();
        assertEquals("Start the game", printSystem.get(0));
    }

    @Test
    public void shouldShowWinnerMessageWhenIsOver() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createGame(printSystem);
        game.init();
        game.run();
        assertEquals("Player 1 quit", printSystem.getLastRegister());
    }

    private Game createGame(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Player playerTest1 = new Player.Builder().setDeck(deck).build();
        Player playerTest2 = new Player(playerTest1, new EndGameStrategy());
        return new Game(printSystem, playerTest2);
    }
}
