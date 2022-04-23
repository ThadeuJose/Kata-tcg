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
                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                                .setTarget(game.getOppositionPlayerTarget())
                                .build();
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
                Game game = new Game(activePlayer, nonActivePlayer);

                Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_DAMAGE)
                                .setTarget(game.getOppositionPlayerTarget())
                                .build();
                game.play(move);
        }

        @Test
        public void activePlayerShouldntPlayCardOutOfIndex() {
                thrown.expect(IndexOutOfBoundsException.class);

                Player activePlayer = new Player.Builder()
                                .setCardsInHand(new Card.Builder(8).build())
                                .build();

                Player nonActivePlayer = new Player.Builder().build();
                Game game = new Game(activePlayer, nonActivePlayer);

                Move move = new Move.Builder().setCardIndex(5).setType(Type.AS_DAMAGE)
                                .setTarget(game.getOppositionPlayerTarget())
                                .build();
                game.play(move);
        }

        @Test
        public void activePlayerShouldntPlayCardOutOfIndex2() {
                thrown.expect(IndexOutOfBoundsException.class);

                Player activePlayer = new Player.Builder()
                                .setCardsInHand(new Card.Builder(8).build())
                                .build();

                Player nonActivePlayer = new Player.Builder().build();
                Game game = new Game(activePlayer, nonActivePlayer);

                Move move = new Move.Builder().setCardIndex(-1).setType(Type.AS_DAMAGE)
                                .setTarget(game.getOppositionPlayerTarget())
                                .build();

                game.play(move);
        }
}
