package com.tcg.system;

import com.tcg.Game;
import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observer;

public class VictorySystem implements Observer {

    Game game;

    public VictorySystem(Game game) {
        this.game = game;
    }

    public void checkWinner() {
        if (game.getActivePlayer().getCurrentHealth() <= 0) {
            game.setWinner(game.getNonActivePlayer());
            game.setVictory();
        }

        if (game.getNonActivePlayer().getCurrentHealth() <= 0) {
            game.setWinner(game.getActivePlayer());
            game.setVictory();
        }
    }

    @Override
    public void update(Message message) {
        checkWinner();
    }

}
