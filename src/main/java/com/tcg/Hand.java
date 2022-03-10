package com.tcg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hand {
    private List<Card> list;

    public Hand() {
        list = new ArrayList<>();
    }

    public Hand(Hand hand) {
        list = hand.list;
    }

    public void add(Card card) {
        list.add(card);
    }

    @Override
    public String toString() {
        return IntStream
                .range(0, list.size())
                .mapToObj(i -> String.format("[%d] %d\n", i, list.get(i).getManaCost()))
                .collect(Collectors.joining());
    }

    public Boolean hasPlayableCards(int manaCost) {
        return list.stream().anyMatch(c -> c.getManaCost() >= manaCost);
    }

    public Card getMaxPlayableCard(int manaCost) {
        return list.stream()
                .filter(e -> e.getManaCost() <= manaCost)
                .max(Comparator.comparing(Card::getManaCost))
                .get();
    }

}
