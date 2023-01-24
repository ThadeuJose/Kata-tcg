package com.tcg.action.playcard;

import com.tcg.CantAffordCardException;
import com.tcg.Card;
import com.tcg.InvalidPlayException;
import com.tcg.Player;
import com.tcg.action.Action;

public abstract class PlayCardAction implements Action {
    protected Card card;
    protected Player affectedPlayer;
    protected int cardIndex;

    public PlayCardAction(Player affectedPlayer, int cardIndex) {
        this.affectedPlayer = affectedPlayer;
        this.cardIndex = cardIndex;
    }

    private void validateIfCanPlayCard(Player affectedPlayer, int cardIndex) {
        Card card = affectedPlayer.getCard(cardIndex);

        if (card.getManaCost() > affectedPlayer.getCurrentMana()) {

            throw new CantAffordCardException(
                    createCantAffordCardExceptionMessage(cardIndex, card.getManaCost(),
                            affectedPlayer.getCurrentMana()));
        }
    }

    private void validateIfHandIsEmpty(Player affectedPlayer) {
        if (affectedPlayer.getHandSize() == 0) {
            throw new InvalidPlayException("Shouldn't try to play with empty hand");
        }
    }

    private void playCard(Player affectedPlayer, int cardIndex, int manaCost) {
        affectedPlayer.spendMana(manaCost);
        affectedPlayer.removeCard(cardIndex);
    }

    private String createCantAffordCardExceptionMessage(int cardIndex, int manaCost, int currentMana) {
        String errorMessage = "Cant afford card at index %d with play cost %d with %d mana";
        return String.format(errorMessage, cardIndex, manaCost, currentMana);
    }

    @Override
    public void execute() {
        validateIfHandIsEmpty(affectedPlayer);
        this.card = affectedPlayer.getCard(cardIndex);
        validateIfCanPlayCard(affectedPlayer, cardIndex);
        playCard(affectedPlayer, cardIndex, card.getManaCost());
        action();
    }

    public abstract void action();
}
