package com.tcg;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tcg.action.Action;
import com.tcg.action.CreateMinionAction;
import com.tcg.action.DoDamageAction;
import com.tcg.action.DrawAction;
import com.tcg.action.HealingAction;
import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
import com.tcg.system.PrintSystem;
import com.tcg.system.VictorySystem;

@Component
public class Game {
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;
    private Player activePlayer;
    private Player nonActivePlayer;
    private Player winner;

    PrintSystem printSystem;
    VictorySystem victorySystem;
    Observable observable;

    public Game(PrintSystem printSystem, Player player1, Player player2) {
        this.printSystem = printSystem;
        activePlayer = player1;
        nonActivePlayer = player2;
        winner = null;
        // TODO Refactor
        victorySystem = new VictorySystem(this);
        observable = new Observable();
        observable.addObserver("victory", victorySystem);
    }

    public void init() {
        if (Objects.nonNull(printSystem)) {
            printSystem.print("Start the game");
        }

        for (int i = 0; i < MAX_HAND_ACTIVE_PLAYER; i++) {
            activePlayer.draw();
        }
        for (int i = 0; i < MAX_HAND_NON_ACTIVE_PLAYER; i++) {
            nonActivePlayer.draw();
        }

    }

    public Player getActivePlayer() {
        return new Player(activePlayer);
    }

    public Player getNonActivePlayer() {
        return new Player(nonActivePlayer);
    }

    public void startTurn() {
        activePlayer.addEmptySlot();
        activePlayer.refill();

        if (activePlayer.getDeckSize() == 0) {
            activePlayer.takeDamage(1);
            checkWinner();
        } else {
            activePlayer.draw();
        }

        afterSetTurn();

    }

    public void action(Move move) {
        Type type = move.getType();
        if (type.equals(Type.AS_PASS)) {
            pass();
        } else if (type.equals(Type.AS_END)) {
            endGame();
        } else {
            play(move);
        }
    }
    public void play(Move move) {
        if (activePlayer.getHandSize() == 0) {
            throw new InvalidPlayException("Shouldn't try to play with empty hand");
        }

        Card card = activePlayer.getCardFromHand(move.getCardIndex());
        if (card.getManaCost() > activePlayer.getCurrentMana()) {

            throw new CantAffordCardException(
                    createCantAffordCardExceptionMessage(move.getCardIndex(), card.getManaCost(),
                            activePlayer.getCurrentMana()));
        }

        activePlayer.spendMana(card.getManaCost());

        Action action = createAction(move, card, activePlayer);
        action.execute();

        checkWinner();
    }

    private Action createAction(Move move, Card card, Player affectedPlayer) {
        Type type = move.getType();

        if (type.equals(Type.AS_HEALING)) {
            return new HealingAction(card, affectedPlayer);
        }

        if (type.equals(Type.AS_MINION)) {
            return new CreateMinionAction(card, affectedPlayer);
        }

        if (type.equals(Type.AS_DRAW)) {
            return new DrawAction(card, affectedPlayer);
        }

        return new DoDamageAction(card, move.getTarget());

    }

    public Target getOppositionPlayerTarget() {
        return nonActivePlayer;
    }

    public Target getMinionTarget(int index) {
        return nonActivePlayer.getMinion(index);
    }

    private String createCantAffordCardExceptionMessage(int cardIndex, int manaCost, int currentMana) {
        String errorMessage = "Cant afford card at index %d with play cost %d with %d mana";
        return String.format(errorMessage, cardIndex, manaCost, currentMana);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void pass() {
        if (Objects.nonNull(printSystem)) {
            printSystem.print("Player 1 pass");
        }

        activePlayer.awakeMinions();

        Player temp = activePlayer;
        activePlayer = nonActivePlayer;
        nonActivePlayer = temp;

        passCommand();
    }

    public void attackPlayerWithMinion(int activePlayerMinionIdx) {
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        attack(nonActivePlayer, alliedMinion);
        attack(alliedMinion, nonActivePlayer);

    }

    private void attack(Target target, Combatant combatant) {
        target.takeDamage(combatant.getAttackValue());
    }

    public void attackMinionWithMinion(int activePlayerMinionIdx, int nonActivePlayerMinionIdx) {
        Minion enemyMinion = nonActivePlayer.getMinion(nonActivePlayerMinionIdx);
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        enemyMinion.takeDamage(alliedMinion.getAttackValue());

        alliedMinion.takeDamage(enemyMinion.getAttackValue());

        activePlayer.cleanMinionsWith0Health();
        nonActivePlayer.cleanMinionsWith0Health();
    }

    public void run() {
        while (isRunning()) {
            startTurn();

            checkWinner();

            while (needPlayerInput()) {
                activePlayer.play(this);
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
