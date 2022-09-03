package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class WinconTest {
    // If the opponent player's Health drops to or below zero the active player wins
    // the game.

    @Test
    public void shouldNotStartWithWinner() {
        Game game = createGame();
        game.init();
        game.startTurn();
        Optional<Player> winnerPlayer = game.getWinner();
        assertFalse(winnerPlayer.isPresent());
    }

    @Test
    public void shouldWinTheGameIfHealthDropToZero() {
        Player playerTest = new Player.Builder()
                .setPlayerName("Player 1")
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();

        Player lostPlayer = new Player.Builder().setHealth(2).build();

        Game game = createGame(playerTest, lostPlayer);

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                .setTarget(game.getOppositionPlayerTarget())
                .build();
        game.play(move);

        assertEquals(0, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest.getName(), winnerPlayer.get().getName());
    }

    @Test
    public void shouldWinTheGameIfHealthDropToBelowZero() {
        Player playerTest = new Player.Builder()
                .setPlayerName("Player 1")
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();

        Player lostPlayer = new Player.Builder().setHealth(1).build();

        Game game = createGame(playerTest, lostPlayer);

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                .setTarget(game.getOppositionPlayerTarget())
                .build();
        game.play(move);

        assertEquals(-1, lostPlayer.getCurrentHealth());
        Optional<Player> winnerPlayer = game.getWinner();
        assertTrue(winnerPlayer.isPresent());
        assertEquals(playerTest.getName(), winnerPlayer.get().getName());
    }

}
