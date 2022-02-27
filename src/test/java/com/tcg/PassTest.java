package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PassTest {
    @Test
    public void shouldPass() {
        Player player1 = new Player.Builder()
                .setPlayerName("Player 1")
                .build();
        Player player2 = new Player.Builder()
                .setPlayerName("Player 2")
                .build();
        Game game = new Game(player1, player2);
        game.pass();
        Player activePlayer = game.getActivePlayer();
        assertEquals(player2.getName(), activePlayer.getName());
    }

    @Test
    public void shouldPassBack() {
        Player player1 = new Player.Builder()
                .setPlayerName("Player 1")
                .build();
        Player player2 = new Player.Builder()
                .setPlayerName("Player 2")
                .build();
        Game game = new Game(player1, player2);
        game.pass();
        game.pass();
        Player activePlayer = game.getActivePlayer();
        assertEquals(player1.getName(), activePlayer.getName());
    }
}
