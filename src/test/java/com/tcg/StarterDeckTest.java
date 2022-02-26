package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class StarterDeckTest {
    // Each player starts with a deck of 20 Damage cards with the following Mana
    // costs: 0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8

    @Test
    public void getManaCost() {
        Card card = new Card(8);
        assertEquals(8, card.getManaCost());
    }

    @Test
    public void shouldMakeStarterDeck() {
        Deck deck = Deck.createStandardDeck();

        List<Card> l = deck.getAllCards();

        // 0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8
        HashMap<Integer, Integer> expected = new HashMap<>();
        // Mana Cost, Quantity
        expected.put(0, 2);
        expected.put(1, 2);
        expected.put(2, 3);
        expected.put(3, 4);
        expected.put(4, 3);
        expected.put(5, 2);
        expected.put(6, 2);
        expected.put(7, 1);
        expected.put(8, 1);
        for (var entry : expected.entrySet()) {
            long amount = l.stream().filter(e -> entry.getKey().equals(e.getManaCost())).count();
            if (amount != entry.getValue()) {
                fail("Mana Cost:" + entry.getKey() + " Amount: " + amount + " not equals " + entry.getValue());
            }

        }

    }

    @Test
    public void shouldMakeStarterDeckInsidePlayer() {
        Player p = new Player();
        p.initDeck();
        assertEquals(20, p.getDeckSize());
    }

}
