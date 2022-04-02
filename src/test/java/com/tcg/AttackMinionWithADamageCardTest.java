package com.tcg;

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

        Game game = new Game(activePlayer, nonActivePlayer);

        game.pass();
        nonActivePlayer.setMana(5);
        game.play(0, Type.AS_MINION);
        game.pass();
        activePlayer.setMana(3);
        game.playAttackAtMinion(0, Type.AS_DAMAGE, 0);

        assertEquals(2, nonActivePlayer.getMinion(0).getHealth());

    }
}
