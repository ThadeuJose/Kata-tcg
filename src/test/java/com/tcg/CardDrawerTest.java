package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardDrawerTest {

        @Test
        public void shouldDrawCards() {
                Deck deck = new Deck.Builder()
                                .addCard(5, 0)
                                .build();

                Player player = new Player.Builder()
                                .setMana(5)
                                .setCardsInHand(new Card.Builder(5).build())
                                .setDeck(deck)
                                .build();
                Player p2 = Player.createPlayerWithStandardDeck();
                Game game = new Game(player, p2);

                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DRAW)
                                .build();
                game.play(move);

                assertEquals(5, player.getHandSize());
        }

        @Test
        public void shouldntDrawExcessiveCards() {
                Deck deck = new Deck.Builder()
                                .addCard(8, 2)
                                .build();

                Player player = new Player.Builder()
                                .setMana(7)
                                .setCardsInHand(new Card.Builder(7).build())
                                .setDeck(deck)
                                .build();
                Player p2 = Player.createPlayerWithStandardDeck();
                Game game = new Game(player, p2);

                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DRAW)
                                .build();
                game.play(move);

                assertEquals(5, player.getHandSize());
        }

        @Test
        public void shouldntEmptyDeck() {
                Deck deck = new Deck.Builder()
                                .addCard(7, 2)
                                .build();

                Player player = new Player.Builder()
                                .setMana(7)
                                .setCardsInHand(new Card.Builder(7).build())
                                .setDeck(deck)
                                .build();
                Player p2 = Player.createPlayerWithStandardDeck();
                Game game = new Game(player, p2);

                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DRAW)
                                .build();
                game.play(move);

                assertEquals(0, player.getDeckSize());
        }

        @Test
        public void shouldDealDamageIfIsEmptyDeck() {
                Player player = new Player.Builder()
                                .setMana(7)
                                .setCardsInHand(new Card.Builder(7).build())
                                .build();
                Player p2 = Player.createPlayerWithStandardDeck();
                Game game = new Game(player, p2);

                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DRAW)
                                .build();

                game.play(move);

                assertEquals(23, player.getCurrentHealth());
        }

}
