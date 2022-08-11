package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.tcg.util.TestPrintSystem;

public class GameIntegrationTest {

    @Test
    public void shouldStartGame() {
        TestPrintSystem printSystem = new TestPrintSystem();
        Game game = new Game(printSystem);
        game.init();
        assertEquals("Start the game", printSystem.get(0));
    }

    @Test
    @Ignore
    public void shouldShowWinnerMessageWhenIsOver() {
        // https://www.baeldung.com/java-testing-system-out-println
        // Make a Log System and inject
        // Check if work
        // print("aqsaq")
        // print("Player 1 Winner")
        fail("Not Implement");
    }

}
