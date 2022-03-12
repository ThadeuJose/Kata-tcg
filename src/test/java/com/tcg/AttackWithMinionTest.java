package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AttackWithMinionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntAttackOpponentOnFirstRound() {
        thrown.expect(CantAttackWithMinion.class);
        thrown.expectMessage("Cant attack with a minion in the same turn is play");
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

        game.play(0, Type.AS_MINION);
        game.attackPlayerWithMinion(0);
    }

    @Test
    public void shouldAttackOpponent() {
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

        game.play(0, Type.AS_MINION);
        game.pass();
        game.pass();
        game.attackPlayerWithMinion(0);

        assertEquals(29, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void shouldAttackOpponentWithMultipleMinions() {
        Deck deck = new Deck.Builder()
                .addCard(1, 1)
                .addCard(1, 5)
                .addCard(1, 2)
                .addCard(1, 2)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck).build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();
        activePlayer.setMana(6);

        game.play(0, Type.AS_MINION);
        game.play(0, Type.AS_MINION);
        game.pass();
        game.pass();
        game.attackPlayerWithMinion(0);
        game.attackPlayerWithMinion(1);

        assertEquals(24, nonActivePlayer.getCurrentHealth());
    }
}
