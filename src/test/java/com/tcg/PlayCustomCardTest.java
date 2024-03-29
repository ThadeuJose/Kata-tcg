package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tcg.model.minion.MinionFactory;

public class PlayCustomCardTest {

    Card card;
    Game game;
    Player activePlayer;
    Player nonActivePlayer;

    @Before
    public void setup() {
        card = new Card.Builder(1)
                .setDamage(3)
                .setHealing(0)
                .setMinion(MinionFactory.createMinion(3, 3))
                .setCardDraw(3)
                .build();

        activePlayer = new Player.Builder().setMana(1).setHealth(20).setCardsInHand(card)
                .setDeck(Deck.createStandardDeck())
                .build();
        nonActivePlayer = new Player.Builder().build();

        game = createGame(activePlayer, nonActivePlayer);
    }

    @Test
    public void shouldDealCorrectDamage() {
        Move move = aMove().dealDamageToPlayer(0);
        game.play(move);
        assertEquals(27, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void shouldHealCorrectValue() {
        Move move = new Move.Builder().heal(0);
        game.play(move);
        assertEquals(20, activePlayer.getCurrentHealth());
    }

    @Test
    public void shouldCreateCorrectMinion() {
        Move move = new Move.Builder().createMinion(0);
        game.play(move);

        Minion minion = activePlayer.getMinion(0);
        assertEquals(3, minion.getPower());
        assertEquals(3, minion.getHealth());
    }

    @Test
    public void shouldDrawCorrectQuantityOfCard() {
        Move move = new Move.Builder().draw(0);
        game.play(move);
        assertEquals(3, activePlayer.getHandSize());
    }
}
