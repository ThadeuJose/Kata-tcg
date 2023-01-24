package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static com.tcg.util.CreateUtils.createGame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlayCardExceptionsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void activePlayerShouldntPlayCardWithEmptyHand() {
        thrown.expect(InvalidPlayException.class);
        thrown.expectMessage("Shouldn't try to play with empty hand");

        Player activePlayer = Player.createPlayerWithEmptyDeck();
        Player nonActivePlayer = Player.createPlayerWithEmptyDeck();
        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = aMove().dealDamageToPlayer(0);

        game.play(move);
    }

    @Test
    public void activePlayerShouldntPlayCardWithCantAfford() {
        thrown.expect(CantAffordCardException.class);
        thrown.expectMessage("Cant afford card at index 0 with play cost 8 with 1 mana");

        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(8).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();

        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = aMove().dealDamageToPlayer(0);

        game.play(move);
    }

    @Test
    public void activePlayerShouldntPlayCardOutOfIndex() {
        thrown.expect(IndexOutOfBoundsException.class);

        Player activePlayer = new Player.Builder()
                .setCardsInHand(new Card.Builder(8).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();

        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = aMove().dealDamageToPlayer(5);
        game.play(move);
    }

    @Test
    public void activePlayerShouldntPlayCardOutOfIndex2() {
        thrown.expect(IndexOutOfBoundsException.class);

        Player activePlayer = new Player.Builder()
                .setCardsInHand(new Card.Builder(8).build())
                .build();

        Player nonActivePlayer = new Player.Builder().build();
        Game game = createGame(activePlayer, nonActivePlayer);

        Move move = aMove().dealDamageToPlayer(-1);

        game.play(move);
    }
}
