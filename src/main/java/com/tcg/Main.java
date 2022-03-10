package com.tcg;

public class Main {
    public static void main(String[] args) {
        Player player1 = new PlayerConsole(
                new Player.Builder().setPlayerName("Player 1").setDeck(Deck.createStandardDeck()).build());
        Player player2 = new PlayerPass(
                new Player.Builder().setPlayerName("Player 2").setDeck(Deck.createStandardDeck()).build());
        Game game = new Game(player1, player2);
        game.init();
        game.shuffleDecks();
        game.chooseStarterPlayer();
        while (game.getWinner().isPresent() == false) {
            game.startTurn();
            game.choose();
        }
        if (game.getWinner().isPresent()) {
            game.writeMessage(game.getWinner().get().getName() + " has won");
        }

    }
}
