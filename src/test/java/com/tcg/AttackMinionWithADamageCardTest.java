package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttackMinionWithADamageCardTest {
    @Test
    public void shouldDealDamageToMinion() {
        Deck deck1 = new Deck.Builder()
                .addCard(1, 3)
                .addCard(1, 5)
                .addCard(1, 2)
                .addCard(1, 2)
                .build();

        Deck deck2 = new Deck.Builder()
                .addCard(1, 5)
                .addCard(1, 5)
                .addCard(1, 2)
                .addCard(1, 2)
                .build();

        Player activePlayer = new Player.Builder().setDeck(deck1).build();
        Player nonActivePlayer = new Player.Builder().setDeck(deck2).build();
        Game game = new Game(activePlayer, nonActivePlayer);
        game.init();
        game.startTurn();

        game.pass();
        nonActivePlayer.setMana(5);
        game.play(0, Type.AS_MINION);
        game.pass();
        activePlayer.setMana(3);
        game.playAttackAtMinion(0, Type.AS_DAMAGE, 0);

        assertEquals(2, nonActivePlayer.getMinion(0).getHealth());

    }
}
