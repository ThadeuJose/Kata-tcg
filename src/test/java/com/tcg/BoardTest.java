package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {
    @Test
    public void shouldAddMinions() {
        Card card = new Card(0);
        Board board = new Board();
        board.add(card.createMinion());
        assertEquals(1, board.size());
    }

    @Test
    public void shouldHave3Minions() {
        Card card = new Card(0);
        Board board = new Board();
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());
        assertEquals(3, board.size());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntHaveMoreThen3MinionsOnBoard() {
        thrown.expect(BoardOverloadException.class);
        thrown.expectMessage("Shouldn't have more then 3 minions on the board");
        Card card = new Card(0);
        Board board = new Board();
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());
    }
}
