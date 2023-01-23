package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateEnum;
import com.tcg.model.state.StateMachine;

public class StateTest {
    @Test
    public void shouldBeginInStartTurnState() {
        StateMachine stateMachine = new StateMachine();
        StateEnum actual = stateMachine.getCurrentState();
        assertEquals(StateEnum.START_TURN, actual);
    }

    @Test
    public void shouldGoFromStartTurnToVictoryStateWhenAPlayerIs0Health() {
        StateMachine stateMachine = new StateMachine();
        stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
        StateEnum actual = stateMachine.getCurrentState();
        assertEquals(StateEnum.VICTORIOUS, actual);
    }

    @Test
    public void shouldGoFromStartTurnToPlayerInputWhenTurnIsSet() {
        StateMachine stateMachine = new StateMachine();
        stateMachine.update(EventEnum.TURN_IS_SET);
        StateEnum actual = stateMachine.getCurrentState();
        assertEquals(StateEnum.PLAYER_INPUT, actual);
    }

    @Test
    public void shouldGoFromStartTurnToVictoryState() {
        StateMachine stateMachine = new StateMachine();

        stateMachine.update(EventEnum.TURN_IS_SET);
        assertEquals(StateEnum.PLAYER_INPUT, stateMachine.getCurrentState());

        stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
        assertEquals(StateEnum.VICTORIOUS, stateMachine.getCurrentState());
    }

    @Test
    public void shouldReturnToStartTurnFromPlayerInput() {
        StateMachine stateMachine = new StateMachine();

        stateMachine.update(EventEnum.TURN_IS_SET);
        assertEquals(StateEnum.PLAYER_INPUT, stateMachine.getCurrentState());

        stateMachine.update(EventEnum.PASS_COMMAND);
        assertEquals(StateEnum.START_TURN, stateMachine.getCurrentState());
    }

    @Test
    public void shouldGoToQuitState() {
        StateMachine stateMachine = new StateMachine();

        stateMachine.update(EventEnum.TURN_IS_SET);
        assertEquals(StateEnum.PLAYER_INPUT, stateMachine.getCurrentState());

        stateMachine.update(EventEnum.QUIT_COMMAND);
        assertEquals(StateEnum.QUIT, stateMachine.getCurrentState());
    }

    @Test
    public void quitStateIsFinalState() {
        StateMachine stateMachine = new StateMachine();

        stateMachine.update(EventEnum.TURN_IS_SET);
        assertEquals(StateEnum.PLAYER_INPUT, stateMachine.getCurrentState());

        stateMachine.update(EventEnum.QUIT_COMMAND);
        assertTrue("Should be final state", stateMachine.isFinalState());
    }

    @Test
    public void victoryStateIsFinalState() {
        StateMachine stateMachine = new StateMachine();

        stateMachine.update(EventEnum.TURN_IS_SET);
        assertEquals(StateEnum.PLAYER_INPUT, stateMachine.getCurrentState());

        stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
        assertTrue("Should be final state", stateMachine.isFinalState());
    }
}
