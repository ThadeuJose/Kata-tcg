package com.tcg;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
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
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;

    Match match;

    PrintSystem printSystem;
    VictorySystem victorySystem;
    ActionSystem actionSystem;
    Observable observable;

    private StateMachine stateMachine;
    private ManaRefillService manaRefillService;

    public Game(StateMachine stateMachine, ManaRefillService manaRefillService, PrintSystem printSystem, Player player1,
            Player player2) {
        this.printSystem = printSystem;

        // TODO Refactor
        match = new Match(player1, player2);

        victorySystem = new VictorySystem(this);
        observable = new Observable();
        observable.addObserver("victory", victorySystem);
        actionSystem = new ActionSystem(this, observable, match);

        this.stateMachine = stateMachine;
        this.manaRefillService = manaRefillService;

    }

    public void init() {
        if (Objects.nonNull(printSystem)) {
            printSystem.print("Start the game");
        }

        for (int i = 0; i < MAX_HAND_ACTIVE_PLAYER; i++) {
            match.getActivePlayer().draw();
        }
        for (int i = 0; i < MAX_HAND_NON_ACTIVE_PLAYER; i++) {
            match.getNonActivePlayer().draw();
        }

    }

    public Player getActivePlayer() {
        return new Player(match.getActivePlayer());
    }

    public Player getNonActivePlayer() {
        return new Player(match.getNonActivePlayer());
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
        Move move = new Move.Builder().setType(Type.AS_MINION_ATTACK_PLAYER)
                .setActivePlayerMinionIdx(activePlayerMinionIdx)
                .build();
        actionSystem.action(move);
    }

    public void attackMinionWithMinion(int activePlayerMinionIdx, int nonActivePlayerMinionIdx) {
        Move move = new Move.Builder().setType(Type.AS_MINION_ATTACK_MINION)
                .setActivePlayerMinionIdx(activePlayerMinionIdx)
                .setNonActivePlayerMinionIdx(nonActivePlayerMinionIdx)
                .build();
        actionSystem.action(move);
    }

    public Target getOppositionPlayerTarget() {
        return match.getNonActivePlayer();
    }

    public Target getMinionTarget(int index) {
        return match.getNonActivePlayer().getMinion(index);
    }

    public Optional<Player> getWinner() {
        return match.getWinner();
    }

    public void setWinner(Player winner) {
        match.setWinner(winner);
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
                action(match.getActivePlayer().play(opponentBoardstate, playerBoardstate));
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

    public void endGame() {
        stateMachine.update(EventEnum.QUIT_COMMAND);
    }

    public void setVictory() {
        stateMachine.update(EventEnum.PLAYER_GO_TO_0_HEALTH);
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
        Message message = new Message("victory", null);
        observable.sendMessage(message);
    }
}
