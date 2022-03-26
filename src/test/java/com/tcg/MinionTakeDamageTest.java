package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinionTakeDamageTest {
    @Test
    public void shouldTakeDamage() {
        Card card = new Card(5);
        Minion minion = card.createMinion();
        minion.takeDamage(3);
        assertEquals(2, minion.getHealth());
    }
}
