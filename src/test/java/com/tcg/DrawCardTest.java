package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DrawCardTest {
    @Test
    public void shouldDrawCard() {
        Deck deck = new Deck.Builder().addCard(1, 0).build();
        assertNotNull(deck.drawCard());
    }

    @Test
    public void shouldBeEmptyAfterDrawACard() {
        Deck deck = new Deck.Builder().addCard(1, 0).build();
        deck.drawCard();
        assertEquals(0, deck.size());
    }
}
