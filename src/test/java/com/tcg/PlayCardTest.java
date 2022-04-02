package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayCardTest {

    Game game;

    @Before
    public void setup() {
        Player activePlayer = new Player.Builder()
                .setMana(3)
                .setCardsInHand(new Card.Builder(0).build(),
                        new Card.Builder(1).build(), new Card.Builder(2).build(), new Card.Builder(2).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();
        game = new Game(activePlayer, nonActivePlayer);

        game.play(0);
        game.play(0);
        game.play(0);
    }

    @Test
    public void activePlayerShouldPlayCard() {
        Player player = game.getActivePlayer();
        assertEquals(1, player.getHandSize());
    }

    @Test
    public void nonActivePlayerShouldLostLife() {
        Player player = game.getNonActivePlayer();
        assertEquals(27, player.getCurrentHealth());
    }

    @Test
    public void activePlayerShouldSpendMana() {
        Player player = game.getActivePlayer();
        assertEquals(0, player.getCurrentMana());
    }

}
