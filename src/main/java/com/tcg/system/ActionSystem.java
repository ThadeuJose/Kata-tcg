package com.tcg.system;

import java.util.HashMap;
import java.util.function.Consumer;

import com.tcg.Minion;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.Type;
import com.tcg.action.Action;
import com.tcg.action.AttackMinionWithMinion;
import com.tcg.action.AttackPlayerWithMinionAction;
import com.tcg.action.EndGameAction;
import com.tcg.action.PassAction;
import com.tcg.action.playcard.CreateMinionAction;
import com.tcg.action.playcard.DoDamageAction;
import com.tcg.action.playcard.DrawAction;
import com.tcg.action.playcard.HealingAction;
import com.tcg.model.Match;
import com.tcg.model.state.StateMachine;

public class ActionSystem {
    private VictorySystem victorySystem;
    private Match match;
    private StateMachine stateMachine;
    private PrintSystem printSystem;

    private HashMap<Type, Consumer<Move>> map;

    public ActionSystem(StateMachine stateMachine, PrintSystem printSystem, VictorySystem victorySystem,
            Match match) {
        this.victorySystem = victorySystem;
        this.match = match;
        this.stateMachine = stateMachine;
        this.printSystem = printSystem;

        map = new HashMap<>();
        map.put(Type.AS_PASS, m -> pass(m));
        map.put(Type.AS_END, m -> endGame(m));
        map.put(Type.AS_MINION_ATTACK_PLAYER, m -> attackPlayerWithMinion(m));
        map.put(Type.AS_MINION_ATTACK_MINION, m -> attackMinionWithMinion(m));
        map.put(Type.AS_DAMAGE_PLAYER, m -> dealDamageToPlayer(m));
        map.put(Type.AS_DAMAGE_MINION, m -> dealDamageToMinion(m));
        map.put(Type.AS_HEALING, m -> heal(m));
        map.put(Type.AS_DRAW, m -> draw(m));
        map.put(Type.AS_MINION, m -> createMinion(m));
    }

    public void action(Move move) {
        Type type = move.getType();
        map.get(type).accept(move);
    }

    private void createMinion(Move move) {
        Action action = new CreateMinionAction(match.getActivePlayer(), move.getCardIndex());
        action.execute();
    }

    private void draw(Move move) {
        Action action = new DrawAction(match.getActivePlayer(), move.getCardIndex(), victorySystem);
        action.execute();
    }

    private void heal(Move move) {
        Action action = new HealingAction(match.getActivePlayer(), move.getCardIndex());
        action.execute();
    }

    private void dealDamageToPlayer(Move move) {
        Action action = new DoDamageAction(match.getActivePlayer(), move.getCardIndex(), victorySystem,
                match.getNonActivePlayer());
        action.execute();
    }

    private void dealDamageToMinion(Move move) {
        Minion target = match.getNonActivePlayer().getMinion(move.getNonActivePlayerMinionIdx());
        Action action = new DoDamageAction(match.getActivePlayer(), move.getCardIndex(), victorySystem, target);
        action.execute();
    }

    private void endGame(Move move) {
        Action action = new EndGameAction(stateMachine);
        action.execute();
    }

    private void pass(Move move) {
        Action action = new PassAction(printSystem, match, stateMachine);
        action.execute();
    }

    private void attackPlayerWithMinion(Move move) {
        int activePlayerMinionIdx = move.getActivePlayerMinionIdx();

        Player activePlayer = match.getActivePlayer();
        Player nonActivePlayer = match.getNonActivePlayer();

        Action action = new AttackPlayerWithMinionAction(activePlayer, nonActivePlayer, activePlayerMinionIdx,
                victorySystem);
        action.execute();
    }

    private void attackMinionWithMinion(Move move) {
        int activePlayerMinionIdx = move.getActivePlayerMinionIdx();
        int nonActivePlayerMinionIdx = move.getNonActivePlayerMinionIdx();

        Player activePlayer = match.getActivePlayer();
        Player nonActivePlayer = match.getNonActivePlayer();

        Action action = new AttackMinionWithMinion(activePlayer, nonActivePlayer, activePlayerMinionIdx,
                nonActivePlayerMinionIdx);
        action.execute();
    }

}
