package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class DrawFromEmptyDeckTest {
    @Test
    public void playerShouldReceive1DamageIfDrawFromEmptyLibrary() {
        Deck deck = DeckFactory.createEmptyDeck();
        Player playerTest = new Player.Builder().setDeck(deck).build();
        Player player2 = Player.createPlayerWithStandardDeck();

        Game game = createGame(playerTest, player2);
        game.startTurn();
        assertEquals(29, playerTest.getCurrentHealth());
    }

    @Test
    public void playerShouldLostIfDrawFromEmptyLibraryAndHaveOnly1Health() {
        Deck deck = DeckFactory.createEmptyDeck();
        Player playerTest = new Player.Builder().setHealth(1).setDeck(deck).build();
        Player player2 = new Player.Builder().setPlayerName("Player 2").build();

        Game game = createGame(playerTest, player2);
        game.startTurn();

        Optional<Player> winner = game.getWinner();
        assertTrue(winner.isPresent());
        assertEquals(player2.getName(), winner.get().getName());
    }
}
