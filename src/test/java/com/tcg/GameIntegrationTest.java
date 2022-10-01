package com.tcg;

import static com.tcg.Move.toPlayer;
import static com.tcg.util.CreateUtils.aMove;
import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tcg.strategy.ConsoleStrategy;
import com.tcg.strategy.EndGameStrategy;
import com.tcg.strategy.PassStrategy;
import com.tcg.strategy.Strategy;
import com.tcg.util.NullInputSytem;
import com.tcg.util.TestPlayerDecorator;
import com.tcg.util.TestPrintSystem;

public class GameIntegrationTest {

    @Test
    public void shouldStartGame() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createAnyGame(printSystem);
        game.init();
        assertEquals("Start the game", printSystem.get(0));
    }

    @Test
    public void shouldShowMessageWhenAPlayerQuit() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = createAnyGame(printSystem);
        game.init();
        game.run();
        assertEquals("Player 1 quit", printSystem.getLastRegister());
    }

    @Test
    public void shouldShowWinnerMessageWhenIsOver() {
        TestPrintSystem printSystem = new TestPrintSystem();

        Strategy strategy = new Strategy() {
            @Override
            public void play(Game game) {
                Move move = aMove().dealDamage(0, toPlayer());
                game.play(move);
                game.pass();
            }
        };

        Card card = new Card.Builder(1).setDamage(2).build();
        Player player1 = new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck())
                .setMana(2)
                .setCardsInHand(card)
                .setStrategy(strategy)
                .build();

        Player player2 = new Player.Builder().setHealth(2).setDeck(Deck.createStandardDeck())
                .setStrategy(new PassStrategy())
                .build();

        Game game = createGame(printSystem, player1, player2);
        game.init();
        game.run();
        assertEquals("Player 1 win", printSystem.getLastRegister());
    }

    @Test
    public void shouldLossInStartTurn() {
        TestPrintSystem printSystem = new TestPrintSystem();

        Strategy strategy = new Strategy() {
            @Override
            public void play(Game game) {
                fail("Should loss before action");
            }
        };

        Deck deck = new Deck.Builder().addCard(3, 2).build();

        Player player1 = new Player.Builder().setHealth(1).setDeck(deck)
                .setStrategy(strategy)
                .build();

        Player player2 = new Player.Builder().setPlayerName("Player 2")
                .setDeck(Deck.createStandardDeck())
                .setStrategy(new PassStrategy())
                .build();

        Game game = createGame(printSystem, player1, player2);
        game.init();
        game.run();
        assertEquals("Player 2 win", printSystem.getLastRegister());
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
        assertEquals("Opponent board:\n", printSystem.get(3));
        assertEquals("Myself: 30 Life", printSystem.get(4));
        assertEquals("Myself: 1 mana", printSystem.get(5));
        assertEquals("My board:\n", printSystem.get(6));
        assertEquals(handString, printSystem.get(7));
    }

    private Game createAnyGame(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Strategy strategy = new EndGameStrategy();
        Player player1 = new Player.Builder().setDeck(deck).setStrategy(strategy).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        return createGame(printSystem, player1, player2);
    }

    private Game createGameWithPlayerConsole(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Strategy strategy = new TestPlayerDecorator(new ConsoleStrategy(printSystem, new NullInputSytem()));
        Player player1 = new Player.Builder().setDeck(deck).setStrategy(strategy).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        return createGame(printSystem, player1, player2);
    }

    private Game createGameWithPlayerPass(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Strategy strategy = new TestPlayerDecorator(new PassStrategy());
        Player player1 = new Player.Builder().setDeck(deck).setStrategy(strategy).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        return createGame(printSystem, player1, player2);
    }
}
