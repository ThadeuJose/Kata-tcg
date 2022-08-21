package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrintPlayerHandTest {

    @Test
    public void shouldPrintCorrectOneCardHand() {
        Card card = new Card.Builder(2).build();
        Player player = new Player.Builder().setCardsInHand(card).build();
        String expected = "[0] Cost 2 - Damage 2 Healing 2 Minion 2/2 Card Draw 2\n";
        String actual = player.printHand();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintCorrectMultiplesCardHand() {
        Card card = new Card.Builder(2).build();
        Card card2 = new Card.Builder(3).build();
        Player player = new Player.Builder().setCardsInHand(card, card2).build();
        String expected = "[0] Cost 2 - Damage 2 Healing 2 Minion 2/2 Card Draw 2\n[1] Cost 3 - Damage 3 Healing 3 Minion 3/3 Card Draw 3\n";
        String actual = player.printHand();
        assertEquals(expected, actual);
    }
}
