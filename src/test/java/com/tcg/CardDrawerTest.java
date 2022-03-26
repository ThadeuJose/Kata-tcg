package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardDrawerTest {

    @Test
    public void shouldDrawCards() {
        Deck deck = new Deck.Builder()
                .addCard(1, 5)
                .addCard(5, 0)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        player.setMana(5);
        player.draw();
        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(player, p2);
        game.play(0, Type.AS_DRAW);

        assertEquals(5, player.getHandSize());
    }

    @Test
    public void shouldntDrawExcessiveCards() {
        Deck deck = new Deck.Builder()
                .addCard(1, 7)
                .addCard(8, 2)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        player.setMana(7);
        player.draw();
        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(player, p2);
        game.play(0, Type.AS_DRAW);

        assertEquals(5, player.getHandSize());
    }

    @Test
    public void shouldntEmptyDeck() {
        Deck deck = new Deck.Builder()
                .addCard(1, 7)
                .addCard(7, 2)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        player.setMana(7);
        player.draw();
        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(player, p2);
        game.play(0, Type.AS_DRAW);

        assertEquals(0, player.getDeckSize());
    }

    @Test
    public void shouldDealDamageIfIsEmptyDeck() {
        Deck deck = new Deck.Builder()
                .addCard(1, 7)
                .build();

        Player player = new Player.Builder().setDeck(deck).build();
        player.setMana(7);
        player.draw();
        Player p2 = Player.createPlayerWithStandardDeck();
        Game game = new Game(player, p2);
        game.play(0, Type.AS_DRAW);

        assertEquals(23, player.getCurrentHealth());
    }

}
