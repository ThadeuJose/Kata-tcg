package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CopyPlayerTest {
    @Test
    public void shouldCreateANewCopyOfPlayer() {
        Game game = new Game();
        Player player = game.getActivePlayer();
        player.addEmptySlot();
        player = game.getActivePlayer();
        assertEquals(0, player.getCurrentManaSlot());
    }
}
