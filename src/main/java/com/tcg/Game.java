package com.tcg;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tcg.model.ManaRefillService;
import com.tcg.model.Match;
import com.tcg.model.state.EventEnum;
import com.tcg.model.state.StateMachine;
import com.tcg.strategy.boardstate.BoardStateMapper;
import com.tcg.strategy.boardstate.OpponentBoardstate;
import com.tcg.strategy.boardstate.PlayerBoardstate;
import com.tcg.system.ActionSystem;
import com.tcg.system.PrintSystem;
import com.tcg.system.VictorySystem;

@Component
public class Game {
    Match match;

    PrintSystem printSystem;
    VictorySystem victorySystem;
    ActionSystem actionSystem;

    private StateMachine stateMachine;
    private ManaRefillService manaRefillService;

    public Game(StateMachine stateMachine, ManaRefillService manaRefillService, PrintSystem printSystem, Match match) {
        this.printSystem = printSystem;

        // TODO Refactor
        this.match = match;

        this.stateMachine = stateMachine;
        this.manaRefillService = manaRefillService;

        victorySystem = new VictorySystem(match, stateMachine);
        actionSystem = new ActionSystem(stateMachine, printSystem, victorySystem, match);

    }

    public void init() {
        if (Objects.nonNull(printSystem)) {
            printSystem.print("Start the game");
        }
    }

    public void startTurn() {
        Player player = match.getActivePlayer();
        manaRefillService.refillPlayerMana(player);

        afterSetTurn();

        if (player.getDeckSize() == 0) {
            player.takeDamage(1);
            checkWinner();
        } else {
            player.draw();
        }

    }

    public void action(Move move) {
        actionSystem.action(move);
    }

    public void play(Move move) {
        actionSystem.action(move);
    }

    public void attackPlayerWithMinion(int activePlayerMinionIdx) {
        Move move = new Move.Builder().attackPlayerWithMinion(activePlayerMinionIdx);
        actionSystem.action(move);
    }

    public void attackMinionWithMinion(int activePlayerMinionIdx, int nonActivePlayerMinionIdx) {
        Move move = new Move.Builder().attackMinionWithMinion(activePlayerMinionIdx, nonActivePlayerMinionIdx);
        actionSystem.action(move);
    }

    public Target getOppositionPlayerTarget() {
        return match.getNonActivePlayer();
    }

    public Target getMinionTarget(int index) {
        return match.getNonActivePlayer().getMinion(index);
    }

    public Optional<Player> getWinner() {
        return victorySystem.getWinner();
    }

    public void pass() {
        if (Objects.nonNull(printSystem)) {
            printSystem.print("Player 1 pass");
        }

        match.getActivePlayer().awakeMinions();

        match.changeActivePlayer();

        passCommand();
    }

    public void run() {
        while (isRunning()) {
            startTurn();

            // checkWinner();

            while (needPlayerInput()) {
                PlayerBoardstate playerBoardstate = BoardStateMapper.mapPlayerBoardstatefromMatch(match);
                OpponentBoardstate opponentBoardstate = BoardStateMapper.mapOpponentBoardstatefromMatch(match);
                actionSystem.action(match.getActivePlayer().play(opponentBoardstate, playerBoardstate));
            }

            // TODO Refactor should end loop after deal damage
            // checkWinner();
        }

        Optional<Player> winner = getWinner();
        if (!winner.isEmpty()) {
            Player player = winner.get();
            printSystem.print(player.getName() + " win");
        } else {
            printSystem.print("Player 1 quit");
        }
    }

    private boolean isRunning() {
        return !stateMachine.isFinalState();
    }

    private boolean needPlayerInput() {
        return stateMachine.stillNeedPlayerInput();
    }

    private void passCommand() {
        stateMachine.update(EventEnum.PASS_COMMAND);
    }

    private void afterSetTurn() {
        stateMachine.update(EventEnum.TURN_IS_SET);
    }

    private void checkWinner() {
        victorySystem.checkWinner();
    }
}
