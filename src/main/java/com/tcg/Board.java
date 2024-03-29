package com.tcg;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int MAX_MINIONS_BOARD = 3;
    private List<Minion> minions;

    public Board() {
        minions = new ArrayList<>();
    }

    public Board(Board board) {
        minions = new ArrayList<>(board.minions);
    }

    public void add(Minion minion) {
        if (size() >= MAX_MINIONS_BOARD) {
            throw new BoardOverloadException("Shouldn't have more then 3 minions on the board");
        } else {
            minions.add(minion);
        }
    }

    public Minion get(int idx) {
        return minions.get(idx);
    }

    public int size() {
        return minions.size();
    }

    public void awakeMinions() {
        minions.stream().forEach(Minion::awake);
    }

    public void cleanMinionsWith0Health() {
        minions.removeIf(e -> e.getHealth() <= 0);
    }

    public String printAllInformation() {
        String result = "";
        for (int i = 0; i < minions.size(); i++) {
            Minion minion = minions.get(i);
            result += "[" + i + "] " + minion.printAllInformation() + "\n";
        }
        return result;
    }

    public String printWithoutInformation() {
        String result = "";
        for (int i = 0; i < minions.size(); i++) {
            Minion minion = minions.get(i);
            result += "[" + i + "] " + minion.printWithoutInformation() + "\n";
        }
        return result;
    }

}
