package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DrawCardTest {
    @Test
    public void shouldDrawCard() {
        Card c = new Card(0);
        Deck d = new Deck();
        d.addCard(c);
        assertEquals(c, d.drawCard());
    }

    @Test
    public void shouldBeEmptyAfterDrawACard() {
        Card c = new Card(0);
        Deck d = new Deck();
        d.addCard(c);
        d.drawCard();
        assertEquals(0, d.size());
    }
}
