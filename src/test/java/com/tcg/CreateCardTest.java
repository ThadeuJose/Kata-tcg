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

    @Test
    public void shouldCreateCorrect0Card() {
        Card expected = new Card.Builder(0).build();
        Minion minion = new Minion(0, 1);
        Card actual = new Card(0, 0, 0, minion, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintCardinformation() {
        Card card = new Card.Builder(0).build();
        String expected = "Cost 0 - Damage 0 Healing 0 Minion 0/1 Card Draw 0";
        String actual = card.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintCustomCardinformation() {
        Card card = new Card.Builder(2)
                .setDamage(3)
                .setHealing(2)
                .setMinion(new Minion.Builder().setPower(3).setHealth(2).build())
                .setCardDraw(1)
                .build();
        String expected = "Cost 2 - Damage 3 Healing 2 Minion 3/2 Card Draw 1";
        String actual = card.toString();
        assertEquals(expected, actual);
    }

}
