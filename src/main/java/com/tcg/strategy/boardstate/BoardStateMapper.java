package com.tcg.strategy.boardstate;

import com.tcg.Player;
import com.tcg.model.Match;

public class BoardStateMapper {

    public static PlayerBoardstate mapPlayerBoardstatefromMatch(Match match) {
        Player player = match.getActivePlayer();

        return new PlayerBoardstate(player.getCurrentHealth(), player.getCurrentMana(), player.getBoard(),
                player.getHand());
    }

    public static OpponentBoardstate mapOpponentBoardstatefromMatch(Match match) {
        Player player = match.getNonActivePlayer();

        return new OpponentBoardstate(player.getCurrentHealth(), player.getHandSize(), player.getBoard());
    }
}
