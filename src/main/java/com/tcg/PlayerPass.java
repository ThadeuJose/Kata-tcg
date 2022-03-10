package com.tcg;

public class PlayerPass extends Player {

    public PlayerPass(Player player) {
        super(player);
    }

    @Override
    public void choose(Game game) {
        game.pass();
    }
}
