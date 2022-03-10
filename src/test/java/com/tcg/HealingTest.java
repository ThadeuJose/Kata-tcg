package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HealingTest {
    @Test
    public void activePlayerCanPlayCardAsHealing() {
        Deck deck = new Deck.Builder()
                .addCard(2, 1)
                .addCard(1, 2)
                .addCard(1, 5)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(player, p2);
        game.init();
        game.startTurn();

        player.setMana(3);
        player.setHealth(29);

        // [1,1,2,5]
        game.play(0, Type.AS_HEALING);
        assertEquals(30, player.getCurrentHealth());
    }

    @Test
    public void activePlayerCanPlayCardAsHealingOrDamage() {
        Deck deck = new Deck.Builder()
                .addCard(2, 1)
                .addCard(1, 2)
                .addCard(1, 5)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck).build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();

        activePlayer.setMana(1);

        // [1,1,2,5]
        game.play(0, Type.AS_HEALING);
        assertEquals(30, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void activePlayerShouldNotHaveMoreThen30Health() {
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

        activePlayer.setHealth(24);
        activePlayer.setMana(7);
        // [1,2,5]
        game.play(1, Type.AS_HEALING);
        game.play(2, Type.AS_HEALING);
        assertEquals(30, nonActivePlayer.getCurrentHealth());
    }
}
