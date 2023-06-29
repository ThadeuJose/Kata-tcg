package com.tcg;

import static com.tcg.util.PlayerBuilder.anyPlayer;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.model.Match;
import com.tcg.model.match.MatchFactory;
import com.tcg.util.TestPlayerChooser;

public class StartGameTest {
    @Test
    public void playerShouldStartWith30Health() {
        Player player = anyPlayer();
        int startingHealth = 30;
        assertEquals(startingHealth, player.getCurrentHealth());
    }

    @Test
    public void playerShouldStartWith0ManaSlot() {
        Player player = anyPlayer();
        int startingManaSlot = 0;
        assertEquals(startingManaSlot, player.getCurrentManaSlot());
    }

    @Test
    public void activePlayerShouldStartWith3CardsInHand() {
        TestPlayerChooser playerChooser = new TestPlayerChooser();
        Match match = MatchFactory.createMatch(anyPlayer(), anyPlayer(), playerChooser);
        Player player = match.getActivePlayer();
        int handSize = 3;
        assertEquals(handSize, player.getHandSize());
    }

    @Test
    public void nonActivePlayerShouldStartWith4CardsInHand() {
        TestPlayerChooser playerChooser = new TestPlayerChooser();
        Match match = MatchFactory.createMatch(anyPlayer(), anyPlayer(), playerChooser);
        Player player = match.getNonActivePlayer();
        int handSize = 4;
        assertEquals(handSize, player.getHandSize());
    }
}
