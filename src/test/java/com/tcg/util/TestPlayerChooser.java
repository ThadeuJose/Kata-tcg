package com.tcg.util;

import com.tcg.Player;
import com.tcg.model.match.PlayerChooser;
import com.tcg.model.match.Players;

public class TestPlayerChooser extends PlayerChooser {

    @Override
    public Players randomChoosePlayers(Player player1, Player player2) {
        return new Players(player1, player2);
    }

}
