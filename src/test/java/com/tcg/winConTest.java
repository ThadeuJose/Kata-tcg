package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class winConTest {
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
        PlayerTest playerTest = new PlayerTest();
        Player lostPlayer = new Player();
        game = new Game(playerTest, lostPlayer);
        game.init();
        game.startTurn();
        // Set mana of player to 2
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
        PlayerTest playerTest = new PlayerTest();
        Player lostPlayer = new Player();
        game = new Game(playerTest, lostPlayer);
        game.init();
        game.startTurn();
        // Set mana of player to 2
        playerTest.setMana(2);
        lostPlayer.setHealth(1);
        // [2,1,2,0]
        game.play(0);
        assertEquals(-1, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest, winnerPlayer.get());
    }

    class PlayerTest extends Player {
        @Override
        public void initDeck() {
            deck.addCard(new Card(2));
            deck.addCard(new Card(1));
            deck.addCard(new Card(2));
            deck.addCard(new Card(0));
        }
    }
}
