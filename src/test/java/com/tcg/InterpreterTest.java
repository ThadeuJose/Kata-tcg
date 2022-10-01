package com.tcg;

import static com.tcg.util.CreateUtils.aMove;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.target.TargetPlayer;
import com.tcg.target.TargetType;

public class InterpreterTest {

    @Test
    public void shouldReturnEndActionIfNoValidCommand() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("");
        Move expected = new Move.Builder().setType(Type.AS_END).build();
        assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void shouldReturnDamageAction() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("D");
        Move expected = aMove().dealDamage(0, Move.toPlayer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());

        TargetType actualClass = actual.getTargetType();
        assertEquals(TargetPlayer.class, actualClass.getClass());
    }

    @Test
    public void shouldReturnPassAction() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("P");
        Move expected = new Move.Builder().setType(Type.AS_PASS).build();
        assertEquals(expected.getType(), actual.getType());
    }
}
