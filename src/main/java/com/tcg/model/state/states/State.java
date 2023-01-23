package com.tcg.model.state.states;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;

public interface State {
    StateEnum update(EventEnum event);

    default boolean isFinalState() {
        return false;
    }
}
