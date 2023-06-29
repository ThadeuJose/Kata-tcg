package com.tcg.model.player;

import com.tcg.Deck;
import com.tcg.Player;
import com.tcg.strategy.Strategy;

public class PlayerFactory {
    public static Player createPlayer(String name, Deck deck, Strategy strategy) {
        return new Player.Builder().setPlayerName(name).setDeck(deck).setStrategy(strategy).build();
    }
}
