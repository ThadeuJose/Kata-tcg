package com.tcg.action;

import com.tcg.model.Match;
import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateMachine;
import com.tcg.system.PrintSystem;

public class PassAction implements Action {

    private PrintSystem printSystem;
    private Match match;
    private StateMachine stateMachine;

    public PassAction(PrintSystem printSystem, Match match, StateMachine stateMachine) {
        this.printSystem = printSystem;
        this.match = match;
        this.stateMachine = stateMachine;
    }

    @Override
    public void execute() {
        printSystem.print(match.getActivePlayer().getName() + " pass");

        match.getActivePlayer().awakeMinions();

        match.changeActivePlayer();

        stateMachine.update(EventEnum.PASS_COMMAND);
    }

}
