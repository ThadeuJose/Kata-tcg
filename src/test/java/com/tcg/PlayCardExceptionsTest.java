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
        EmptyHandPlayerTest playerTest = new EmptyHandPlayerTest();
        Game game = new Game(playerTest, new Player());
        game.play(0);

    }

    class EmptyHandPlayerTest extends Player {
        @Override
        public void initDeck() {

        }
    }

    @Test
    public void activePlayerShouldntPlayCardWWithCantAfford() {
        thrown.expect(CantAffordCardException.class);
        thrown.expectMessage("Cant afford card at index 0 with play cost 8 with 1 mana");
        CantAffordHandPlayerTest playerTest = new CantAffordHandPlayerTest();
        Game game = new Game(playerTest, new Player());
        game.init();
        game.startTurn();
        game.play(0);
    }

    class CantAffordHandPlayerTest extends Player {
        @Override
        public void initDeck() {
            deck.addCard(new Card(8));
            deck.addCard(new Card(8));
            deck.addCard(new Card(8));
            deck.addCard(new Card(8));
        }
    }
}
