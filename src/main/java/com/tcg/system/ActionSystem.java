package com.tcg.system;

import java.util.HashMap;
import java.util.function.Consumer;

import com.tcg.CantAffordCardException;
import com.tcg.Card;
import com.tcg.Game;
import com.tcg.InvalidPlayException;
import com.tcg.Minion;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.Type;
import com.tcg.action.Action;
import com.tcg.action.CreateMinionAction;
import com.tcg.action.DoDamageAction;
import com.tcg.action.DrawAction;
import com.tcg.action.HealingAction;
import com.tcg.model.Match;

public class ActionSystem {
    Game game;
    VictorySystem victorySystem;
    Match match;

    private HashMap<Type, Consumer<Move>> map;

    public ActionSystem(Game game, VictorySystem victorySystem, Match match) {
        this.game = game;
        this.victorySystem = victorySystem;
        this.match = match;

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

    private void createMinion(Move move) {
        Player activePlayer = match.getActivePlayer();

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

        Action action = new CreateMinionAction(card, activePlayer);
        action.execute();
    }

    private void draw(Move move) {
        Player activePlayer = match.getActivePlayer();

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

        Action action = new DrawAction(card, activePlayer);
        action.execute();

        checkWinner();
    }

    public void action(Move move) {
        Type type = move.getType();
        map.get(type).accept(move);

    }

    private void endGame(Move move) {
        game.endGame();
    }

    private void pass(Move move) {
        game.pass();
    }

    private void heal(Move move) {
        Player activePlayer = match.getActivePlayer();

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

        Action action = new HealingAction(card, activePlayer);
        action.execute();
    }

    private String createCantAffordCardExceptionMessage(int cardIndex, int manaCost, int currentMana) {
        String errorMessage = "Cant afford card at index %d with play cost %d with %d mana";
        return String.format(errorMessage, cardIndex, manaCost, currentMana);
    }

    private void checkWinner() {
        victorySystem.checkWinner();
    }

    private void attackPlayerWithMinion(Move move) {
        int activePlayerMinionIdx = move.getActivePlayerMinionIdx();

        Minion alliedMinion = match.getActivePlayer().getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        match.getNonActivePlayer().takeDamage(alliedMinion.getAttackValue());
    }

    private void attackMinionWithMinion(Move move) {
        int activePlayerMinionIdx = move.getActivePlayerMinionIdx();
        int nonActivePlayerMinionIdx = move.getNonActivePlayerMinionIdx();

        Minion alliedMinion = match.getActivePlayer().getMinion(activePlayerMinionIdx);

        alliedMinion.validateIfCanAttack();

        Minion enemyMinion = match.getNonActivePlayer().getMinion(nonActivePlayerMinionIdx);

        enemyMinion.takeDamage(alliedMinion.getAttackValue());
        alliedMinion.takeDamage(enemyMinion.getAttackValue());

        match.getActivePlayer().cleanMinionsWith0Health();
        match.getNonActivePlayer().cleanMinionsWith0Health();
    }

    private void dealDamageToPlayer(Move move) {
        Player activePlayer = match.getActivePlayer();

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

        Action action = new DoDamageAction(card, match.getNonActivePlayer());
        action.execute();

        checkWinner();
    }

    private void dealDamageToMinion(Move move) {
        Player activePlayer = match.getActivePlayer();

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

        Action action = new DoDamageAction(card,
                match.getNonActivePlayer().getMinion(move.getNonActivePlayerMinionIdx()));
        action.execute();

        checkWinner();
    }

}
