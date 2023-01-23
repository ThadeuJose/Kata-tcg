package com.tcg.model;

import com.tcg.Player;

public class ManaRefillService {

    public void refillPlayerMana(Player player) {
        player.addEmptySlot();
        player.refill();
    }

}
