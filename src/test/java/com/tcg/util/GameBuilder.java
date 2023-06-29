package com.tcg.util;

import java.util.Objects;

import com.tcg.Game;
import com.tcg.Player;
import com.tcg.model.ManaRefillService;
import com.tcg.model.Match;
import com.tcg.model.state.StateMachine;

public class GameBuilder {

    private TestPrintSystem printSystem = new TestPrintSystem();
    private ManaRefillService manaRefillService = new TestManaRefillService();
    private Player activePlayer = PlayerBuilder.anyPlayer1().build();
    private Player nonActivePlayer = PlayerBuilder.anyPlayer2().build();
    private Match match = null;

    public static GameBuilder aGame() {
        return new GameBuilder();
    }

    public static Game anyGame() {
        return aGame().build();
    }

    public GameBuilder withManaRefilService(ManaRefillService manaRefillService) {
        this.manaRefillService = manaRefillService;
        return this;
    }

    public GameBuilder withMatch(Match match) {
        this.match = match;
        return this;
    }

    public GameBuilder withActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
        return this;
    }

    public GameBuilder withNonActivePlayer(Player nonActivePlayer) {
        this.nonActivePlayer = nonActivePlayer;
        return this;
    }

    public Game build() {
        return new Game(new StateMachine(), manaRefillService, printSystem, createMatch());
    }

    private Match createMatch() {
        if (Objects.isNull(match)) {
            return MatchBuilder.aMatch().withActivePlayer(activePlayer).withNonActivePlayer(nonActivePlayer).build();
        }
        return match;
    }
}
