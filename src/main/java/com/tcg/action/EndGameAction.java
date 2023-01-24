package com.tcg.action;

import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateMachine;

public class EndGameAction implements Action {

    private StateMachine stateMachine;

    public EndGameAction(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void execute() {
        stateMachine.update(EventEnum.QUIT_COMMAND);
    }

}
