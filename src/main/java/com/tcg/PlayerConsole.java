package com.tcg;

import java.util.Scanner;

public class PlayerConsole extends Player {

    public PlayerConsole(Player player) {
        super(player);
    }

    @Override
    public void choose(Game game) {
        while (true) {
            Player nonActivePlayer = game.getNonActivePlayer();
            Player activePlayer = game.getActivePlayer();

            game.writeMessage(activePlayer.getName() + " Health: " + activePlayer.getCurrentHealth() + "\n"
                    + nonActivePlayer.getName() + " Health: " + nonActivePlayer.getCurrentHealth()
                    + "\n\n" +
                    "Current Mana: " + getCurrentMana() + "/" + getCurrentManaSlot() + "\n");

            String handPrint = "";
            for (int i = 0; i < hand.size(); i++) {
                handPrint += "[" + i + "] " + hand.get(i).getManaCost() + "\n";
            }
            game.writeMessage(handPrint);
            if (game.getWinner().isPresent()) {
                break;
            }

            String input = inputFromCommandLine();
            if (input.equalsIgnoreCase("")) {
                game.pass();
                break;
            } else {
                game.play(Integer.parseInt(input));
            }

        }

    }

    public String inputFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
