package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OverloadTest {
    @Test
    public void shouldNotDrawMoreThen5Cards() {
        Player player = new Player.Builder().setDeck(Deck.createStandardDeck()).build();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        assertEquals(5, player.getHandSize());
    }

    @Test
    public void shouldDiscardCardNotPutBack() {
        Player player = new Player.Builder().setDeck(Deck.createStandardDeck()).build();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        assertEquals(14, player.getDeckSize());
    }
}
