package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ManaSlotTest {
    @Test
    public void shouldStartWith0Slot() {
        Player player = new Player();
        assertEquals(0, player.getSlots());
    }

    @Test
    public void shouldAdd1Slot() {
        Player player = new Player();
        player.addEmptySlot();
        assertEquals(1, player.getSlots());
    }

    @Test
    public void shouldHaveEmptyMana() {
        Player player = new Player();
        player.addEmptySlot();
        assertEquals(0, player.getCurrentMana());
    }

    @Test
    public void shouldRefillMana() {
        Player player = new Player();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.refill();
        assertEquals(3, player.getCurrentMana());
    }

    @Test
    public void shouldNotHaveMoreThen10() {
        Player player = new Player();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        player.addEmptySlot();
        assertEquals(10, player.getCurrentManaSlot());
    }
}
