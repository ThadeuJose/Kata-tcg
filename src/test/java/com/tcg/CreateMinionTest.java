package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CreateMinionTest {
    @Test
    public void shouldCreateValidMinionOfManaCost0Card() {
        Card card = new Card.Builder(0).build();
        Minion minion = card.createMinion();
        assertEquals(0, minion.getPower());
        assertEquals(1, minion.getHealth());
    }

    @Test
    public void shouldCreateDefaultMinionnOfManaCost5Card() {
        Card card = new Card.Builder(5).build();
        Minion minion = card.createMinion();
        assertEquals(5, minion.getPower());
        assertEquals(5, minion.getHealth());
    }

    @Test
    public void shouldCreateDefaultMinion() {
        Minion minion = Minion.buildDefaultMinion(5);
        assertEquals(5, minion.getPower());
        assertEquals(5, minion.getHealth());
    }

    @Test
    public void shouldCreateDefaultMinionWith0Status() {
        Minion minion = Minion.buildDefaultMinion(0);
        assertEquals(0, minion.getPower());
        assertEquals(1, minion.getHealth());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntCreateMinionWithNegativePower() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Minion shouldn't have less then 0 power");
        new Minion.Builder().setPower(-1).build();
    }

    @Test
    public void shouldntCreateMinionWith0orlessHealth() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Minion shouldn't have less then 1 health");
        new Minion.Builder().setHealth(0).build();
    }

}
