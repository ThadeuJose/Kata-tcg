package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlayMinionTest {

    @Test
    public void shouldPlayMinion() {

        Deck deck = new Deck.Builder()
                .addCard(1, 1)
                .addCard(1, 2)
                .addCard(1, 5)
                .addCard(1, 1)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck).build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();

        activePlayer.setMana(1);
        // [1,2,5]
        game.play(0, Type.AS_MINION);
        assertEquals(1, activePlayer.getBoardSize());
    }

    @Test
    public void shouldPlayMultipleMinions() {

        Deck deck = new Deck.Builder()
                .addCard(3, 1)
                .addCard(1, 2)
                .addCard(1, 5)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck).build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();

        activePlayer.setMana(3);
        // [1,1,1]
        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
        assertEquals(3, activePlayer.getBoardSize());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntPlayMoreThen4Minions() {
        thrown.expect(BoardOverloadException.class);
        thrown.expectMessage("Shouldn't have more then 3 minions on the board");

        Deck deck = new Deck.Builder()
                .addCard(4, 1)
                .addCard(1, 2)
                .addCard(1, 5)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck).build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();

        activePlayer.setMana(4);
        activePlayer.draw();

        // [1,1,1,1]
        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
    }
}
