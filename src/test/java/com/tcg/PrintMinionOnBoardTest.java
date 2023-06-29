package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.model.minion.MinionFactory;

public class PrintMinionOnBoardTest {

    @Test
    public void shouldPrintOneMinion() {
        Board board = new Board();
        board.add(MinionFactory.createMinion(2, 2));
        String expected = "[0] 2/2 [Sleep]\n";
        String actual = board.printAllInformation();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintMultiplesMinions() {
        Board board = new Board();
        board.add(MinionFactory.createMinion(2, 2));
        Minion minion = MinionFactory.createMinion(2, 3);
        minion.awake();
        board.add(minion);
        String expected = "[0] 2/2 [Sleep]\n[1] 2/3 [Can Attack]\n";
        String actual = board.printAllInformation();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintMultiplesMinionsNoSpace() {
        Board board = new Board();
        board.add(MinionFactory.createMinion(2, 2));
        Minion minion = MinionFactory.createMinion(2, 3);
        minion.awake();
        minion.attack();
        board.add(minion);
        String expected = "[0] 2/2 [Sleep]\n[1] 2/3\n";
        String actual = board.printAllInformation();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPrintMultiplesMinionsWithoutInformation() {
        Board board = new Board();
        board.add(MinionFactory.createMinion(2, 2));
        Minion minion = MinionFactory.createMinion(2, 3);
        minion.awake();
        board.add(minion);
        String expected = "[0] 2/2\n[1] 2/3\n";
        String actual = board.printWithoutInformation();
        assertEquals(expected, actual);
    }

}
