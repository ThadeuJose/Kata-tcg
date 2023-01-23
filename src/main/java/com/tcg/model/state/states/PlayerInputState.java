package com.tcg.model.state.states;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;

public class PlayerInputState implements State {

    @Override
    public StateEnum update(EventEnum event) {
        if (event.equals(EventEnum.PLAYER_GO_TO_0_HEALTH)) {
            return StateEnum.VICTORIOUS;
        }

        if (event.equals(EventEnum.PASS_COMMAND)) {
            return StateEnum.START_TURN;
        }

        if (event.equals(EventEnum.QUIT_COMMAND)) {
            return StateEnum.QUIT;
        }

        throw new IllegalArgumentException("Pass illegal event " + event.toString() + " to Player Input State");
    }

}
