package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int MAX_MINIONS_BOARD = 3;
    private List<Minion> minions;

    public Board() {
        minions = new ArrayList<>();
    }

    public void add(Minion minion) throws BoardOverloadException {
        if (size() >= MAX_MINIONS_BOARD) {
            throw new BoardOverloadException("Shouldn't have more then 3 minions on the board");
        } else {
            minions.add(minion);
        }
    }

    public int size() {
        return minions.size();
    }

}
