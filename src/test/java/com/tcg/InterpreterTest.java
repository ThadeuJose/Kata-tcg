package com.tcg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterpreterTest {

    @Test
    public void shouldReturnEndActionIfNoValidCommand() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("", null);
        Move expected = new Move.Builder().setType(Type.AS_END).build();
        assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void shouldReturnDamageAction() {
        Interpreter interpreter = new Interpreter();
        Target anyTarget = new Player.Builder().build();
        Move actual = interpreter.createMove("D", anyTarget);
        Move expected = new Move.Builder().setType(Type.AS_DAMAGE).setTarget(anyTarget).setCardIndex(0).build();
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getTarget(), actual.getTarget());
        assertEquals(expected.getCardIndex(), actual.getCardIndex());
    }

    @Test
    public void shouldReturnPassAction() {
        Interpreter interpreter = new Interpreter();
        Move actual = interpreter.createMove("P", null);
        Move expected = new Move.Builder().setType(Type.AS_PASS).build();
        assertEquals(expected.getType(), actual.getType());
    }
}
