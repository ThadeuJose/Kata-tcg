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
        Deck deck = new Deck.Builder()
                .addCard(2, 2)
                .addCard(1, 1)
                .addCard(1, 0)
                .build();

        Player playerTest = new Player.Builder()
                .setDeck(deck)
                .build();

        Player lostPlayer = Player.createPlayerWithEmptyDeck();
        game = new Game(playerTest, lostPlayer);
        // Set mana of player to 2
        playerTest.draw();
        playerTest.setMana(2);
        lostPlayer.setHealth(2);
        // [2,1,2,0]
        game.play(0);
        assertEquals(0, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest, winnerPlayer.get());
    }

    @Test
    public void shouldWinTheGameIfHealthDropToBelowZero() {
        Deck deck = new Deck.Builder()
                .addCard(2, 2)
                .addCard(1, 1)
                .addCard(1, 0)
                .build();

        Player playerTest = new Player.Builder()
                .setDeck(deck)
                .build();

        Player lostPlayer = Player.createPlayerWithEmptyDeck();
        game = new Game(playerTest, lostPlayer);
        // Set mana of player to 2
        playerTest.draw();
        playerTest.setMana(2);
        lostPlayer.setHealth(1);
        // [2,1,2,0]
        game.play(0);
        assertEquals(-1, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest, winnerPlayer.get());
    }

}
