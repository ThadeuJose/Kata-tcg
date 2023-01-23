package com.tcg.model.state;

import java.util.EnumMap;

import com.tcg.model.state.states.PlayerInputState;
import com.tcg.model.state.states.QuitState;
import com.tcg.model.state.states.StartTurnState;
import com.tcg.model.state.states.State;
import com.tcg.model.state.states.VictoryState;

public class StateMachine {

    private StateEnum currentState;
    private EnumMap<StateEnum, State> stateMap;

    public StateMachine() {
        currentState = StateEnum.START_TURN;
        stateMap = new EnumMap<>(StateEnum.class);
        stateMap.put(StateEnum.START_TURN, new StartTurnState());
        stateMap.put(StateEnum.PLAYER_INPUT, new PlayerInputState());
        stateMap.put(StateEnum.VICTORIOUS, new VictoryState());
        stateMap.put(StateEnum.QUIT, new QuitState());
    }

    public StateEnum getCurrentState() {
        return currentState;
    }

    public boolean isFinalState() {
        return stateMap.get(currentState).isFinalState();
    }

    public boolean stillNeedPlayerInput() {
        return currentState.equals(StateEnum.PLAYER_INPUT);
    }

    public void update(EventEnum event) {
        State state = stateMap.get(currentState);
        currentState = state.update(event);
    }

}
