package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayCardTest {

    Game game;

    @Before
    public void setup() {
        Deck deck = new Deck.Builder()
                .addCard(1, 0)
                .addCard(1, 1)
                .addCard(2, 2)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        Player p2 = Player.createPlayerWithStandardDeck();
        game = new Game(player, p2);
        game.init();
        game.startTurn();
        // Set mana of player to 3
        player.setMana(3);
        // [0,1,2,2]
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
