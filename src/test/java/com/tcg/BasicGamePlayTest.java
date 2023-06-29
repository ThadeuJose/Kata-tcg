package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.model.ManaRefillService;
import com.tcg.util.GameBuilder;
import com.tcg.util.PlayerBuilder;

public class BasicGamePlayTest {
    @Test
    public void activePlayerShouldReceive1ManaSlot() {
        Player activePlayer = PlayerBuilder.anyPlayer();
        Game game = GameBuilder.aGame().withManaRefilService(new ManaRefillService()).withActivePlayer(activePlayer)
                .build();
        game.run();
        assertEquals(1, activePlayer.getCurrentManaSlot());
    }

    @Test
    public void activePlayerShouldRefillEmptyManaSlot() {
        Player activePlayer = PlayerBuilder.anyPlayer();
        Game game = GameBuilder.aGame().withManaRefilService(new ManaRefillService()).withActivePlayer(activePlayer)
                .build();
        game.run();
        assertEquals(1, activePlayer.getCurrentMana());
    }

    @Test
    public void activePlayerShouldDrawACard() {
        Player activePlayer = PlayerBuilder.anyPlayer();
        Game game = GameBuilder.aGame().withManaRefilService(new ManaRefillService()).withActivePlayer(activePlayer)
                .build();
        game.run();
        assertEquals(4, activePlayer.getHandSize());
    }

    @Test
    public void activePlayerShouldDrawACardFromDeck() {
        Player activePlayer = PlayerBuilder.anyPlayer();
        Game game = GameBuilder.aGame().withManaRefilService(new ManaRefillService()).withActivePlayer(activePlayer)
                .build();
        game.run();
        assertEquals(16, activePlayer.getDeckSize());
    }
}
