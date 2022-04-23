package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HealingTest {
    @Test
    public void activePlayerCanPlayCardAsHealing() {
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setHealth(29)
                .setCardsInHand(new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();
        Game game = new Game(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_HEALING)
                .build();

        game.play(move);

        assertEquals(30, activePlayer.getCurrentHealth());
    }

    @Test
    public void activePlayerCanPlayCardAsHealingOrDamage() {
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();
        Game game = new Game(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_HEALING)
                .build();
        game.play(move);

        assertEquals(30, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void activePlayerShouldNotHaveMoreThen30Health() {
        Player activePlayer = new Player.Builder()
                .setHealth(24)
                .setMana(7)
                .setCardsInHand(new Card.Builder(7).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();
        Game game = new Game(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_HEALING)
                .build();

        game.play(move);

        assertEquals(30, nonActivePlayer.getCurrentHealth());
    }
}
