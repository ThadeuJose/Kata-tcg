package com.tcg.util;

import com.tcg.Deck;
import com.tcg.DeckFactory;
import com.tcg.Player;
import com.tcg.model.player.PlayerFactory;
import com.tcg.strategy.EndGameStrategy;
import com.tcg.strategy.Strategy;

public class PlayerBuilder {
    private String name = "Player";
    private Deck deck = DeckFactory.createStandardDeck();
    private Strategy strategy = new EndGameStrategy();

    public static PlayerBuilder aPlayer() {
        return new PlayerBuilder();
    }

    public static Player anyPlayer() {
        return aPlayer().build();
    }

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Player build() {
        return PlayerFactory.createPlayer(name, deck, strategy);
    }
}
