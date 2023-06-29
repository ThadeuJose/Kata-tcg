package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;

import com.tcg.strategy.ConsoleStrategy;
import com.tcg.strategy.EndGameStrategy;
import com.tcg.strategy.PassStrategy;
import com.tcg.strategy.Strategy;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;
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

        Strategy strategy2 = mock(Strategy.class);
        when(strategy2.play(any(), any())).thenReturn(aMove().dealDamageToPlayer(0),
                aMove().pass());

        Card card = new Card.Builder(1).setDamage(2).build();
        Player player1 = new Player.Builder().setPlayerName("Player 1")
                .setDeck(DeckFactory.createStandardDeck())
                .setMana(2)
                .setCardsInHand(card)
                .setStrategy(strategy2)
                .build();

        Player player2 = new Player.Builder().setHealth(2).setDeck(DeckFactory.createStandardDeck())
                .setStrategy(new PassStrategy())
                .build();

        Game game = createGame(printSystem, player1, player2);
        game.init();
        game.run();
        assertEquals("Player 1 win", printSystem.getLastRegister());
    }

    @Test
    @Ignore
    public void shouldLossInStartTurn() {
        TestPrintSystem printSystem = new TestPrintSystem();

        Strategy strategy = mock(Strategy.class);

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

        verifyNoInteractions(strategy);
        assertEquals("Player 2 win", printSystem.getLastRegister());
    }

    @Test
    public void shouldPassTurn() {
        TestPrintSystem printSystem = new TestPrintSystem();

        Player player1 = new Player.Builder().setPlayerName("Player 1")
                .setDeck(DeckFactory.createStandardDeck())
                .setStrategy(new PassStrategy()).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2")
                .setDeck(DeckFactory.createStandardDeck())
                .setStrategy(new EndGameStrategy()).build();

        Game game = createGame(printSystem, player1, player2);
        game.init();
        game.run();
        assertEquals("Player 1 pass", printSystem.get(1));
    }

    @Test
    @Ignore
    public void shouldPrintBoardState() {
        PrintSystem printSystem = mock(PrintSystem.class);
        InputSystem inputSystem = mock(InputSystem.class);
        when(inputSystem.getInput()).thenReturn("");

        ConsoleStrategy consoleStrategy = new ConsoleStrategy(printSystem, inputSystem);

        Player player1 = new Player.Builder().setPlayerName("Player 1")
                .setDeck(DeckFactory.createStandardDeck())
                .setStrategy(consoleStrategy)
                .build();
        Player player2 = new Player.Builder().setPlayerName("Player 2")
                .setDeck(DeckFactory.createStandardDeck())
                .setStrategy(new EndGameStrategy())
                .build();

        Game game = createGame(player1, player2);
        game.init();
        game.run();

        InOrder inOrder = inOrder(printSystem);

        String handString = "Hand: \n[0] Cost 0 - Damage 0 Healing 0 Minion 0/1 Card Draw 0\n[1] Cost 0 - Damage 0 Healing 0 Minion 0/1 Card Draw 0\n[2] Cost 1 - Damage 1 Healing 1 Minion 1/1 Card Draw 1\n[3] Cost 1 - Damage 1 Healing 1 Minion 1/1 Card Draw 1\n";
        inOrder.verify(printSystem).print("Opponent: 30 Life");
        inOrder.verify(printSystem).print("Opponent: 4 cards in hand");

        inOrder.verify(printSystem).print("Opponent board:\n");
        inOrder.verify(printSystem).print("Myself: 30 Life");
        inOrder.verify(printSystem).print("Myself: 1 mana");
        inOrder.verify(printSystem).print("My board:\n");
        inOrder.verify(printSystem).print(handString);
    }

    private Game createAnyGame(TestPrintSystem printSystem) {
        Deck deck = Deck.createStandardDeck();
        Strategy strategy = new EndGameStrategy();
        Player player1 = new Player.Builder().setDeck(deck).setStrategy(strategy).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck())
                .build();
        return createGame(printSystem, player1, player2);
    }

}
