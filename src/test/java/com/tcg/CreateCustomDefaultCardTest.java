package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreateCustomDefaultCardTest {

    Card card;

    @Before
    public void setup() {
        card = new Card.Builder(2).build();
    }

    @Test
    public void shouldHaveCorrectManaCost() {
        assertEquals(2, card.getManaCost());
    }

    @Test
    public void shouldHaveCorrectDamage() {
        assertEquals(2, card.getDamageValue());
    }

    @Test
    public void shouldHaveCorrectHealing() {
        assertEquals(2, card.getHealingValue());
    }

    @Test
    public void shouldCreateCorrectMinion() {
        Minion minion = card.createMinion();
        assertEquals(2, minion.getPower());
        assertEquals(2, minion.getHealth());
    }

    @Test
    public void shouldHaveCorrectQuantityOfCardToDraw() {
        assertEquals(2, card.quantityOfCardsToDraw());
    }
}
