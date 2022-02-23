package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StartGameTest {
    @Test
    public void activePlayerShouldStartWith30Health() {
        Game game = new Game();
        Player player = game.getActivePlayer();
        int startingHealth = 30;
        assertEquals(startingHealth, player.getCurrentHealth());
    }

    @Test
    public void nonActivePlayerShouldStartWith30Health() {
        Game game = new Game();
        Player player = game.getNonActivePlayer();
        int startingHealth = 30;
        assertEquals(startingHealth, player.getCurrentHealth());
    }

    @Test
    public void activePlayerShouldStartWith0ManaSlot() {
        Game game = new Game();
        Player player = game.getActivePlayer();
        int startingManaSlot = 0;
        assertEquals(startingManaSlot, player.getCurrentManaSlot());
    }

    @Test
    public void nonActivePlayerShouldStartWith0ManaSlot() {
        Game game = new Game();
        Player player = game.getNonActivePlayer();
        int startingManaSlot = 0;
        assertEquals(startingManaSlot, player.getCurrentManaSlot());
    }

    @Test
    public void activePlayerShouldStartWith3CardsInHand() {
        Game game = new Game();
        game.init();
        Player player = game.getActivePlayer();
        int handSize = 3;
        assertEquals(handSize, player.getHandSize());
    }

    @Test
    public void nonActivePlayerShouldStartWith4CardsInHand() {
        Game game = new Game();
        game.init();
        Player player = game.getNonActivePlayer();
        int handSize = 4;
        assertEquals(handSize, player.getHandSize());
    }
}
