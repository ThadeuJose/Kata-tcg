package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlayMinionTest {

    @Test
    public void shouldPlayMinion() {

        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();

        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().createMinion(0);
        game.play(move);
        assertEquals(1, activePlayer.getBoardSize());
    }

    @Test
    public void shouldPlayMultipleMinions() {

        Player activePlayer = new Player.Builder()
                .setMana(3)
                .setCardsInHand(new Card.Builder(1).build(), new Card.Builder(1).build(),
                        new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();

        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().createMinion(0);
        game.play(move);
        game.play(move);
        game.play(move);

        assertEquals(3, activePlayer.getBoardSize());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntPlayMoreThen4Minions() {
        thrown.expect(BoardOverloadException.class);
        thrown.expectMessage("Shouldn't have more then 3 minions on the board");

        Player activePlayer = new Player.Builder()
                .setMana(4)
                .setCardsInHand(new Card.Builder(1).build(),
                        new Card.Builder(1).build(), new Card.Builder(1).build(),
                        new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();

        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = new Move.Builder().createMinion(0);
        game.play(move);
        game.play(move);
        game.play(move);
        game.play(move);
    }
}
