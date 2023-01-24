package com.tcg.system;

import java.util.Optional;

import com.tcg.Player;
import com.tcg.model.Match;
import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateMachine;

public class VictorySystem {

    private Player winner;
    private Match match;
    private StateMachine stateMachine;

    public VictorySystem(Match match, StateMachine stateMachine) {
        this.winner = null;
        this.match = match;
        this.stateMachine = stateMachine;
    }

    public void checkWinner() {
        if (match.getActivePlayer().getCurrentHealth() <= 0) {
            winner = match.getNonActivePlayer();
            stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
        }

        if (match.getNonActivePlayer().getCurrentHealth() <= 0) {
            winner = match.getActivePlayer();
            stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
        }
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

}
