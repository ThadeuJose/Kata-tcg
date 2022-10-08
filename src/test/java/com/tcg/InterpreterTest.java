package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.target.TargetPlayer;
import com.tcg.target.TargetType;

public class InterpreterTest {

    @Test
    public void shouldReturnEndMoveIfNoValidCommand() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("");
        Move expected = new Move.Builder().endTurn();
        assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void shouldReturnPassMove() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("P");
        Move expected = new Move.Builder().setType(Type.AS_PASS).build();
        assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void shouldReturnDamageMove() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("D");
        Move expected = aMove().dealDamage(0, Move.toPlayer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());

        TargetType actualClass = actual.getTargetType();
        assertEquals(TargetPlayer.class, actualClass.getClass());
    }

    @Test
    public void shouldReturnDamageMoveWithIndexDifferentOfZero() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("D5");
        Move expected = aMove().dealDamage(5, Move.toPlayer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());

        TargetType actualClass = actual.getTargetType();
        assertEquals(TargetPlayer.class, actualClass.getClass());
    }

    @Test
    public void shouldReturnHealMove() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("H");
        Move expected = aMove().heal(0);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnHealMoveWithIndexDifferentOfZero() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("H4");
        Move expected = aMove().heal(4);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnDrawMove() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("R");
        Move expected = aMove().draw(0);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnDrawMoveWithIndexDifferentOfZero() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("R4");
        Move expected = aMove().draw(4);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnCreateMinionMove() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("C");
        Move expected = aMove().createMinion(0);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnCreateMinionMoveWithIndexDifferentOfZero() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("C3");
        Move expected = aMove().createMinion(3);

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }
}
