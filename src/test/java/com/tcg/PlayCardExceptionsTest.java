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
                game.play(0, Type.AS_DAMAGE);
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

                game.play(0, Type.AS_DAMAGE);
        }

        @Test
        public void activePlayerShouldntPlayCardOutOfIndex() {
                thrown.expect(IndexOutOfBoundsException.class);

                Player activePlayer = new Player.Builder()
                                .setCardsInHand(new Card.Builder(8).build())
                                .build();

                Player nonActivePlayer = new Player.Builder().build();
                Game game = new Game(activePlayer, nonActivePlayer);
                game.play(5, Type.AS_DAMAGE);
        }

        @Test
        public void activePlayerShouldntPlayCardOutOfIndex2() {
                thrown.expect(IndexOutOfBoundsException.class);

                Player activePlayer = new Player.Builder()
                                .setCardsInHand(new Card.Builder(8).build())
                                .build();

                Player nonActivePlayer = new Player.Builder().build();
                Game game = new Game(activePlayer, nonActivePlayer);
                game.play(-1, Type.AS_DAMAGE);
        }
}
