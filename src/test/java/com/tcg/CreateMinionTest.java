package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tcg.model.minion.MinionFactory;

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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntCreateMinionWithNegativePower() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Minion shouldn't have less then 0 power");
        MinionFactory.createMinion(-1, 0);
    }

    @Test
    public void shouldntCreateMinionWith0orlessHealth() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Minion shouldn't have less then 1 health");
        MinionFactory.createMinion(0, 0);
    }

}
