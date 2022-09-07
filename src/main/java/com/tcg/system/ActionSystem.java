package com.tcg.system;

import com.tcg.CantAffordCardException;
import com.tcg.Card;
import com.tcg.Game;
import com.tcg.InvalidPlayException;
import com.tcg.Move;
import com.tcg.Player;
import com.tcg.Type;
import com.tcg.action.Action;
import com.tcg.action.CreateMinionAction;
import com.tcg.action.DoDamageAction;
import com.tcg.action.DrawAction;
import com.tcg.action.HealingAction;
import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
import com.tcg.model.Match;

public class ActionSystem {
    Game game;
    Observable observable;
    Match match;

    public ActionSystem(Game game, Observable observable, Match match) {
        this.game = game;
        this.observable = observable;
        this.match = match;
    }

    public void action(Move move) {
        Type type = move.getType();
        if (type.equals(Type.AS_PASS)) {
            game.pass();
        } else if (type.equals(Type.AS_END)) {
            game.endGame();
        } else {
            play(move);
        }
    }

    public void play(Move move) {
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

    private String createCantAffordCardExceptionMessage(int cardIndex, int manaCost, int currentMana) {
        String errorMessage = "Cant afford card at index %d with play cost %d with %d mana";
        return String.format(errorMessage, cardIndex, manaCost, currentMana);
    }

    private void checkWinner() {
        Message message = new Message("victory", null);
        observable.sendMessage(message);
    }

}