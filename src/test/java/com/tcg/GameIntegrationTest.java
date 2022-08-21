package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.tcg.strategy.EndGameStrategy;
import com.tcg.util.TestPlayerConsoleStrategy;
import com.tcg.util.TestPlayerPassStrategy;
import com.tcg.util.TestPrintSystem;

@Ignore
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

    @Test
    public void shouldPassTurn() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createGameWithPlayerPass(printSystem);
        game.init();
        game.run();
        assertEquals("Player 1 pass", printSystem.get(1));
    }

    @Test
    public void shouldShowMessage() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createGameWithPlayerConsole(printSystem);
        game.init();
        game.run();
        String handString = "Hand: \n[0] Cost 0 - Damage 0 Healing 0 Minion 0/1 Card Draw 0\n[1] Cost 0 - Damage 0 Healing 0 Minion 0/1 Card Draw 0\n[2] Cost 1 - Damage 1 Healing 1 Minion 1/1 Card Draw 1\n[3] Cost 1 - Damage 1 Healing 1 Minion 1/1 Card Draw 1\n";
        assertEquals("Opponent: 30 Life", printSystem.get(1));
        assertEquals("Opponent: 4 cards in hand", printSystem.get(2));
        assertEquals("Myself: 30 Life", printSystem.get(3));
        assertEquals(handString, printSystem.get(4));
    }

    private Game createGame(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Player playerTest1 = new Player.Builder().setDeck(deck).build();
        Player playerTest2 = new Player(playerTest1, new EndGameStrategy());
        return new Game(printSystem, playerTest2);
    }

    private Game createGameWithPlayerConsole(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Player player = new Player.Builder().setDeck(deck).build();
        Player playerTest1 = new Player(player, new TestPlayerConsoleStrategy(printSystem));
        return new Game(printSystem, playerTest1);
    }

    private Game createGameWithPlayerPass(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Player player = new Player.Builder().setDeck(deck).build();
        Player playerTest1 = new Player(player, new TestPlayerPassStrategy());
        return new Game(printSystem, playerTest1);
    }
}
