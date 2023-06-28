package com.tcg.model;

import org.springframework.stereotype.Service;

import com.tcg.Player;

@Service
public class ManaRefillService {

    public void refillPlayerMana(Player player) {
        player.addEmptySlot();
        player.refill();
    }

}
