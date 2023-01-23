package com.tcg.model.state.states;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;

public class VictoryState implements State {

    @Override
    public StateEnum update(EventEnum event) {
        throw new IllegalArgumentException("Pass illegal event " + event.toString() + " to Victory State");
    }

    @Override
    public boolean isFinalState() {
        return true;
    }
}
