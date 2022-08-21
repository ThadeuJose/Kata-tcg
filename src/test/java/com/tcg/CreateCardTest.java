package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreateCardTest {

    @Test
    public void shouldCreateCorrectCustomCard() {
        Card actual = new Card.Builder(2)
                .setDamage(3)
                .setHealing(2)
                .setMinion(new Minion.Builder().setPower(3).setHealth(2).build())
                .setCardDraw(1)
                .build();
        Minion minion = new Minion(3, 2);
        Card expected = new Card(2, 3, 2, minion, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateCorrectDefaultCard() {
        Card expected = new Card.Builder(2).build();
        Minion minion = new Minion(2, 2);
        Card actual = new Card(2, 2, 2, minion, 2);
        assertEquals(expected, actual);
    }

}
