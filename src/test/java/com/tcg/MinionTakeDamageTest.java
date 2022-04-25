package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinionTakeDamageTest {
    @Test
    public void shouldTakeDamage() {
        Card card = new Card.Builder(5).build();
        Minion minion = card.createMinion();
        minion.takeDamage(3);
        assertEquals(2, minion.getHealth());
    }
}
