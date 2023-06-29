package com.tcg.model.match;

import org.springframework.stereotype.Component;

import com.tcg.Player;

@Component
public class PlayerChooser {

    // TODO Implement
    public Players randomChoosePlayers(Player player1, Player player2) {
        return new Players(player1, player2);
    }
}
