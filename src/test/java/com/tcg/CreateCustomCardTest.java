package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreateCustomCardTest {

    Card card;

    @Before
    public void setup() {
        card = new Card.Builder(2)
                .setDamage(3)
                .setHealing(2)
                .setMinion(new Minion.Builder().setPower(3).setHealth(2).build())
                .setCardDraw(1)
                .build();
    }

    @Test
    public void shouldHaveCorrectManaCost() {
        assertEquals(2, card.getManaCost());
    }

    @Test
    public void shouldHaveCorrectDamage() {
        assertEquals(3, card.getDamageValue());
    }

    @Test
    public void shouldHaveCorrectHealing() {
        assertEquals(2, card.getHealingValue());
    }

    @Test
    public void shouldCreateCorrectMinion() {
        Minion minion = card.createMinion();
        assertEquals(3, minion.getPower());
        assertEquals(2, minion.getHealth());
    }

    @Test
    public void shouldHaveCorrectQuantityOfCardToDraw() {
        assertEquals(1, card.quantityOfCardsToDraw());
    }
}
