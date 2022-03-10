package com.tcg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PlayerConsoleTest {
    @Test
    public void shouldShowStatus() {
        PlayerTest playerTest = new PlayerTest(
                new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build());
        Player p2 = new Player.Builder().setPlayerName("Player 2").build();
        GameTest game = new GameTest(playerTest, p2);
        game.startTurn();
        playerTest.choose(game);

        String expected = "Player 1 Health: 30\nPlayer 2 Health: 30\n\nCurrent Mana: 1/1\n";
        assertEquals(expected, game.getLog(0));
    }

    @Test
    public void shouldShowHand() {
        PlayerTest playerTest = new PlayerTest(
                new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build());
        Player p2 = new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build();
        GameTest game = new GameTest(playerTest, p2);
        game.init();
        playerTest.choose(game);

        String expected = "[0] 0\n[1] 0\n[2] 1\n";
        assertEquals(expected, game.getLog(1));
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
        }

        @Override
        public void writeMessage(String message) {
            logMessage.add(message);
        }

        public String getLog(int index) {
            return logMessage.get(index);
        }
    }

    public class PlayerTest extends PlayerConsole {

        public PlayerTest(Player player) {
            super(player);
        }

        @Override
        public String inputFromCommandLine() {
            return "";
        }

    }
}
