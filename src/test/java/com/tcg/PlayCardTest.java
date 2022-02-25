package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayCardTest {

    Game game;

    @Before
    public void setup() {
        PlayerTest playerTest = new PlayerTest();
        game = new Game(playerTest, new Player());
        game.init();
        game.startTurn();
        // Set mana of player to 3
        playerTest.setMana(3);
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

    class PlayerTest extends Player {
        @Override
        public void initDeck() {
            deck.addCard(new Card(0));
            deck.addCard(new Card(1));
            deck.addCard(new Card(2));
            deck.addCard(new Card(2));
        }
    }
}
