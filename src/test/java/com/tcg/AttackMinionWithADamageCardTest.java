package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.tcg.model.ManaRefillService;

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

        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();
        game.pass();

        game.startTurn();
        Move move = new Move.Builder().createMinion(0);
        game.play(move);
        game.pass();

        game.startTurn();
        move = aMove().dealDamageToMinion(0, 0);
        game.play(move);

        assertEquals(2, nonActivePlayer.getMinion(0).getHealth());

    }
}
