package com.tcg.model.match;

import com.tcg.Player;
import com.tcg.model.Match;

public class MatchFactory {
    private static final int STARTER_HAND_SIZE_ACTIVE_PLAYER = 3;
    private static final int STARTER_HAND_SIZE_NON_ACTIVE_PLAYER = 4;

    public static Match createMatch(Player player1, Player player2, PlayerChooser chooser) {
        Players players = chooser.randomChoosePlayers(player1, player2);

        Player activePlayer = players.getActivePlayer();
        activePlayer.draw(STARTER_HAND_SIZE_ACTIVE_PLAYER);

        Player nonActivePlayer = players.getNonActivePlayer();
        nonActivePlayer.draw(STARTER_HAND_SIZE_NON_ACTIVE_PLAYER);

        return new Match(activePlayer, nonActivePlayer);
    }
}
