package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {
    @Test
    public void shouldAddMinions() {
        Card card = new Card.Builder(0).build();
        Board board = new Board();
        board.add(card.createMinion());
        assertEquals(1, board.size());
    }

    @Test
    public void shouldHave3Minions() {
        Card card = new Card.Builder(0).build();
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
        Card card = new Card.Builder(0).build();
        Board board = new Board();
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());
    }

    @Test
    public void shouldntNotAllowToGetMinionWhenDontHave() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index 0 out of bounds for length 0");
        Board board = new Board();
        board.get(0);
    }

    @Test
    public void shouldAwakeAllMinions() {
        Card card = new Card.Builder(0).build();
        Board board = new Board();
        board.add(card.createMinion());
        board.add(card.createMinion());
        board.add(card.createMinion());

        board.awakeMinions();

        assertTrue(board.get(0).isAwake());
        assertTrue(board.get(1).isAwake());
        assertTrue(board.get(2).isAwake());
    }
}
