package com.tcg.util;

import com.tcg.Player;
import com.tcg.model.Match;
import com.tcg.model.match.MatchFactory;

public class MatchBuilder {
    private TestPlayerChooser chooser = new TestPlayerChooser();
    private Player activePlayer = PlayerBuilder.anyPlayer1().build();
    private Player nonActivePlayer = PlayerBuilder.anyPlayer2().build();

    public static MatchBuilder aMatch() {
        return new MatchBuilder();
    }

    public static Match anyMatch() {
        return aMatch().build();
    }

    public MatchBuilder withActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
        return this;
    }

    public MatchBuilder withNonActivePlayer(Player nonActivePlayer) {
        this.nonActivePlayer = nonActivePlayer;
        return this;
    }

    public Match build() {
        return MatchFactory.createMatch(activePlayer, nonActivePlayer, chooser);
    }
}
