package com.tcg.model.state.states;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;

public class StartTurnState implements State {

    @Override
    public StateEnum update(EventEnum event) {
        if (event.equals(EventEnum.PLAYER_GO_TO_0_HEALTH)) {
            return StateEnum.VICTORIOUS;
        }

        if (event.equals(EventEnum.TURN_IS_SET)) {
            return StateEnum.PLAYER_INPUT;
        }
        throw new IllegalArgumentException("Pass illegal event " + event.toString() + " to Start Turn State");
    }

}
