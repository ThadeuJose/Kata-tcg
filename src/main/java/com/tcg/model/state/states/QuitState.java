package com.tcg.model.state.states;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;

public class QuitState implements State {

    @Override
    public StateEnum update(EventEnum event) {
        throw new IllegalArgumentException("Pass illegal event " + event.toString() + " to Quit State");
    }

    @Override
    public boolean isFinalState() {
        return true;
    }

}