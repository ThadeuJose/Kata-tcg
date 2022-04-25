package com.tcg;

import java.util.Optional;

import com.tcg.action.Action;
import com.tcg.action.CreateMinionAction;
import com.tcg.action.DoDamageAction;
import com.tcg.action.DrawAction;
import com.tcg.action.HealingAction;
import com.tcg.attack.Attack;
import com.tcg.attack.AttackMinionWithMinion;
import com.tcg.attack.AttackPlayerWithMinion;

public class Game {
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;
    private Player activePlayer;
    private Player nonActivePlayer;
    private Player winner;

    public Game() {
        // No Test constructor
        Player p1 = new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build();
        Player p2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        this.activePlayer = p1;
        this.nonActivePlayer = p2;
        this.winner = null;
    }

    public Game(Player activePlayer, Player nonActivePlayer) {
        // Test constructor
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
        this.winner = null;
    }

    public void init() {
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
            activePlayer.setHealth(activePlayer.getCurrentHealth() - 1);
            if (activePlayer.getCurrentHealth() <= 0) {
                winner = nonActivePlayer;
            }
        } else {
            activePlayer.draw();
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

        if (activePlayer.getCurrentHealth() <= 0) {
            winner = nonActivePlayer;
        }
        if (nonActivePlayer.getCurrentHealth() <= 0) {
            winner = activePlayer;
        }
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

    public void pass() {
        activePlayer.awakeMinions();
        Player temp = activePlayer;
        activePlayer = nonActivePlayer;
        nonActivePlayer = temp;
    }

    public void attackPlayerWithMinion(int activePlayerMinionIdx) {
        Attack attack = new AttackPlayerWithMinion();
        attack(activePlayerMinionIdx, attack);
    }

    public void attackMinionWithMinion(int activePlayerMinionIdx, int nonActivePlayerMinionIdx) {
        Minion enemyMinion = nonActivePlayer.getMinion(nonActivePlayerMinionIdx);
        Attack attack = new AttackMinionWithMinion(activePlayer, enemyMinion);
        attack(activePlayerMinionIdx, attack);
    }

    private void attack(int activePlayerMinionIdx, Attack attack) {
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);
        alliedMinion.validateIfCanAttack();
        attack.execute(nonActivePlayer, alliedMinion);
    }

}
