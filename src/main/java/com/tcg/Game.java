package com.tcg;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
import com.tcg.model.Match;
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

    public Game(PrintSystem printSystem, Player player1, Player player2) {
        this.printSystem = printSystem;

        // TODO Refactor
        match = new Match(player1, player2);

        victorySystem = new VictorySystem(this);
        observable = new Observable();
        observable.addObserver("victory", victorySystem);
        actionSystem = new ActionSystem(this, observable, match);

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
        player.addEmptySlot();
        player.refill();

        if (player.getDeckSize() == 0) {
            player.takeDamage(1);
            checkWinner();
        } else {
            player.draw();
        }

        afterSetTurn();

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

            checkWinner();

            while (needPlayerInput()) {
                match.getActivePlayer().play(this);
            }

            checkWinner();
        }

        Optional<Player> winner = getWinner();
        if (!winner.isEmpty()) {
            Player player = winner.get();
            printSystem.print(player.getName() + " win");
        } else {
            printSystem.print("Player 1 quit");
        }
    }

    private enum State {
        START_TURN, PLAYER_INPUT, QUIT, VICTORIOUS
    };

    private State currentState = State.START_TURN;

    public void endGame() {
        currentState = State.QUIT;
    }

    public void setVictory() {
        currentState = State.VICTORIOUS;
    }

    private boolean isRunning() {
        return !currentState.equals(State.QUIT) && !currentState.equals(State.VICTORIOUS);
    }

    private boolean needPlayerInput() {
        return currentState.equals(State.PLAYER_INPUT);
    }

    private void passCommand() {
        currentState = State.START_TURN;
    }

    private void afterSetTurn() {
        currentState = State.PLAYER_INPUT;
    }

    private void checkWinner() {
        Message message = new Message("victory", null);
        observable.sendMessage(message);
    }
}
