package com.tcg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PassPlayerTest {
    @Test
    public void shouldCallPass() {
        Player playerTest = new PlayerPass(
                new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build());
        Player p2 = new Player.Builder().build();
        GameTest gameTest = new GameTest(playerTest, p2);
        playerTest.choose(gameTest);
        assertEquals(1, gameTest.passCallCounter);
    }

    @Test
    public void shouldLogMessage() {
        Player playerTest = new PlayerPass(
                new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build());
        Player p2 = new Player.Builder().build();
        GameTest gameTest = new GameTest(playerTest, p2);
        playerTest.choose(gameTest);
        assertEquals("Player 1 pass\n", gameTest.getLog(0));
    }

    public class GameTest extends Game {
        public int passCallCounter = 0;
        private List<String> logMessage = new ArrayList<>();

        public GameTest(Player playerTest, Player p2) {
            super(playerTest, p2);
        }

        @Override
        public void pass() {
            passCallCounter++;
            super.pass();
        }

        @Override
        public void writeMessage(String message) {
            logMessage.add(message);
        }

        public String getLog(int index) {
            return logMessage.get(index);
        }
    }
}
