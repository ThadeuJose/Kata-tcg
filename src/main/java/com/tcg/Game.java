package com.tcg;

import java.util.Optional;

public class Game {
    private static final int MAX_HAND_ACTIVE_PLAYER = 3;
    private static final int MAX_HAND_NON_ACTIVE_PLAYER = 4;
    private Player activePlayer;
    private Player nonActivePlayer;
    private Player winner;

    public Game() {
        this(new Player(), new Player());
    }

    public Game(Player activePlayer, Player nonActivePlayer) {
        this.activePlayer = activePlayer;
        this.nonActivePlayer = nonActivePlayer;
        this.winner = null;
    }

    public void init() {
        activePlayer.initDeck();
        nonActivePlayer.initDeck();
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
        activePlayer.draw();
    }

    public void play(int cardIndex) throws InvalidPlayException {
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
        nonActivePlayer.setHealth(nonActivePlayer.getCurrentHealth() - card.getManaCost());
        if (nonActivePlayer.getCurrentHealth() <= 0) {
            winner = activePlayer;
        }
    }

    private String createCantAffordCardExceptionMessage(int cardIndex, int manaCost, int currentMana) {
        String errorMessage = "Cant afford card at index %d with play cost %d with %d mana";
        return String.format(errorMessage, cardIndex, manaCost, currentMana);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public void pass() {
        Player temp = activePlayer;
        activePlayer = nonActivePlayer;
        nonActivePlayer = temp;
    }

}
