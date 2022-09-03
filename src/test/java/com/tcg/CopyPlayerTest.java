package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CopyPlayerTest {
    @Test
    public void shouldCreateANewCopyOfPlayer() {
        Game game = createGame();
        Player player = game.getActivePlayer();
        player.addEmptySlot();
        player = game.getActivePlayer();
        assertEquals(0, player.getCurrentManaSlot());
    }
}
