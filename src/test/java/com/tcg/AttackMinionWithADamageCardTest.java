package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttackMinionWithADamageCardTest {
    @Test
    public void shouldDealDamageToMinion() {
        Player activePlayer = new Player.Builder()
                .setMana(3)
                .setCardsInHand(new Card.Builder(3).build())
                .build();
        Player nonActivePlayer = new Player.Builder()
                .setMana(5)
                .setCardsInHand(new Card.Builder(5).build())
                .build();

        Game game = createGame(activePlayer, nonActivePlayer);

        game.pass();
        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .setTarget(game.getOppositionPlayerTarget())
                .build();
        game.play(move);
        game.pass();
        move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                .setTarget(game.getMinionTarget(0))
                .build();
        game.play(move);

        assertEquals(2, nonActivePlayer.getMinion(0).getHealth());

    }
}
