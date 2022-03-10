package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HandTest {
    @Test
    public void shouldPrintHand() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(0));
        hand.add(new Card(1));
        String expected = "[0] 0\n[1] 0\n[2] 1\n";
        assertEquals(expected, hand.toString());
    }

    @Test
    public void shouldCheckIfHasPlayableCards() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(0));
        hand.add(new Card(1));
        assertTrue(hand.hasPlayableCards(0));
    }

    @Test
    public void shouldCheckIfHasNotPlayableCards() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(0));
        hand.add(new Card(1));
        assertFalse(hand.hasPlayableCards(2));
    }

    @Test
    public void shouldGetMaxPlayableCard() {
        Hand hand = new Hand();
        Card maxCard = new Card(1);
        hand.add(new Card(0));
        hand.add(maxCard);
        hand.add(new Card(8));
        assertEquals(maxCard.getManaCost(), hand.getMaxPlayableCard(2).getManaCost());
    }

    @Test
    public void shouldGetMaxPlayableCard2() {
        Hand hand = new Hand();
        Card maxCard = new Card(2);
        hand.add(new Card(8));
        hand.add(new Card(0));
        hand.add(new Card(1));
        hand.add(maxCard);
        hand.add(new Card(3));
        assertEquals(maxCard.getManaCost(), hand.getMaxPlayableCard(2).getManaCost());
    }
}
