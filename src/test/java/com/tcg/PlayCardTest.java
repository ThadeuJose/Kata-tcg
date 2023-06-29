package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayCardTest {

    Game game;
    Player activePlayer;
    Player nonActivePlayer;

    @Before
    public void setup() {
        activePlayer = new Player.Builder()
                .setMana(3)
                .setCardsInHand(new Card.Builder(0).build(),
                        new Card.Builder(1).build(), new Card.Builder(2).build(), new Card.Builder(2).build())
                .build();

        nonActivePlayer = new Player.Builder().build();
        game = createGame(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().dealDamageToPlayer(0);

        game.play(move);
        game.play(move);
        game.play(move);

    }

    @Test
    public void activePlayerShouldPlayCard() {
        assertEquals(1, activePlayer.getHandSize());
    }

    @Test
    public void nonActivePlayerShouldLostLife() {
        assertEquals(27, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void activePlayerShouldSpendMana() {
        assertEquals(0, activePlayer.getCurrentMana());
    }

}
