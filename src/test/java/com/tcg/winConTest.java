package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class WinconTest {
    // If the opponent player's Health drops to or below zero the active player wins
    // the game.

    Game game;

    @Test
    public void shouldNotStartWithWinner() {
        game = new Game();
        game.init();
        game.startTurn();
        Optional<Player> winnerPlayer = game.getWinner();
        assertFalse(winnerPlayer.isPresent());
    }

    @Test
    public void shouldWinTheGameIfHealthDropToZero() {
        Player playerTest = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();

        Player lostPlayer = new Player.Builder().setHealth(2).build();

        game = new Game(playerTest, lostPlayer);
        game.play(0, Type.AS_DAMAGE);

        assertEquals(0, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest, winnerPlayer.get());
    }

    @Test
    public void shouldWinTheGameIfHealthDropToBelowZero() {
        Player playerTest = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();

        Player lostPlayer = new Player.Builder().setHealth(1).build();

        game = new Game(playerTest, lostPlayer);
        game.play(0, Type.AS_DAMAGE);

        assertEquals(-1, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest, winnerPlayer.get());
    }

}
