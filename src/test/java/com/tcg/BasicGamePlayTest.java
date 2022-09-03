package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicGamePlayTest {
    @Test
    public void activePlayerShouldReceive1ManaSlot() {
        Game game = createGame();
        game.init();
        game.startTurn();
        Player player = game.getActivePlayer();
        assertEquals(1, player.getCurrentManaSlot());
    }

    @Test
    public void activePlayerShouldRefillEmptyManaSlot() {
        Game game = createGame();
        game.init();
        game.startTurn();
        Player player = game.getActivePlayer();
        assertEquals(1, player.getCurrentMana());
    }

    @Test
    public void activePlayerShouldDrawACard() {
        Game game = createGame();
        game.init();
        game.startTurn();
        Player player = game.getActivePlayer();
        assertEquals(4, player.getHandSize());
    }

    @Test
    public void activePlayerShouldDrawACardFromDeck() {
        Game game = createGame();
        game.init();
        game.startTurn();
        Player player = game.getActivePlayer();
        assertEquals(16, player.getDeckSize());
    }
}
