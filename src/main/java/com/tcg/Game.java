package com.tcg;

import java.util.Optional;

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

    public void play(int cardIndex, Type type) {
        play(cardIndex, type, createPlayerTarget());
    }

    public void play(int cardIndex, Type type, Target target) {
        if (activePlayer.getHandSize() == 0) {
            throw new InvalidPlayException("Shouldn't try to play with empty hand");
        }
        Card card = activePlayer.getCardFromHand(cardIndex);
        if (card.getManaCost() > activePlayer.getCurrentMana()) {

            throw new CantAffordCardException(
                    createCantAffordCardExceptionMessage(cardIndex, card.getManaCost(),
                            activePlayer.getCurrentMana()));
        }
        activePlayer.setMana(activePlayer.getCurrentMana() - card.getManaCost());

        if (type.equals(Type.AS_HEALING)) {
            activePlayer.setHealth(activePlayer.getCurrentHealth() + card.getManaCost());
        } else if (type.equals(Type.AS_MINION)) {
            activePlayer.addMinionOnBoard(card.createMinion());
        } else if (type.equals(Type.AS_DRAW)) {
            for (int i = 0; i < card.quantityOfCardsToDraw(); i++) {
                if (activePlayer.getDeckSize() == 0) {
                    activePlayer.setHealth(activePlayer.getCurrentHealth() - 1);
                } else {
                    activePlayer.draw();
                }
            }
        } else if (type.equals(Type.AS_DAMAGE)) {
            target.takeDamage(card.getManaCost());
        }

        if (activePlayer.getCurrentHealth() <= 0) {
            winner = nonActivePlayer;
        }
        if (nonActivePlayer.getCurrentHealth() <= 0) {
            winner = activePlayer;
        }
    }

    public Target createPlayerTarget() {
        return nonActivePlayer;
    }

    public Target createMinionTarget(int index) {
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

    public void attackPlayerWithMinion(int activePlayerMinionIdx) throws CantAttackWithMinion {
        Minion minion = activePlayer.getMinion(activePlayerMinionIdx);
        minion.validateIfCanAttack();
        nonActivePlayer.setHealth(nonActivePlayer.getCurrentHealth() - minion.getPower());
    }

    public void attackMinionWithMinion(int nonActivePlayerMinionIdx, int activePlayerMinionIdx)
            throws CantAttackWithMinion {
        Minion alliedMinion = activePlayer.getMinion(activePlayerMinionIdx);
        alliedMinion.validateIfCanAttack();
        Minion enemyMinion = nonActivePlayer.getMinion(nonActivePlayerMinionIdx);
        enemyMinion.takeDamage(alliedMinion.getPower());
        alliedMinion.takeDamage(enemyMinion.getPower());
        activePlayer.cleanMinionsWith0Health();
        nonActivePlayer.cleanMinionsWith0Health();
    }

}
