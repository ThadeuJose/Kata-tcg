package com.tcg;

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
        Player playerTest = Player.createPlayerWithEmptyDeck();
        Player p2 = Player.createPlayerWithEmptyDeck();
        Game game = new Game(playerTest, p2);
        game.play(0);
    }

    @Test
    public void activePlayerShouldntPlayCardWWithCantAfford() {
        thrown.expect(CantAffordCardException.class);
        thrown.expectMessage("Cant afford card at index 0 with play cost 8 with 1 mana");

        Deck deck = new Deck.Builder()
                .addCard(4, 8)
                .build();

        Player playerTest = new Player.Builder()
                .setDeck(deck)
                .build();

        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(playerTest, p2);
        game.init();
        game.startTurn();
        game.play(0);
    }
}
