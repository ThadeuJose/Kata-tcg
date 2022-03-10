package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreateMinionTest {
    @Test
    public void shouldCreateValidMinionOfManaCost0Card() {
        Card card = new Card(0);
        Minion minion = card.CreateMinion();
        assertEquals(0, minion.getPower());
        assertEquals(1, minion.getHealth());
    }

    @Test
    public void shouldCreateMinion() {
        Card card = new Card(5);
        Minion minion = card.CreateMinion();
        assertEquals(5, minion.getPower());
        assertEquals(5, minion.getHealth());
    }
}
