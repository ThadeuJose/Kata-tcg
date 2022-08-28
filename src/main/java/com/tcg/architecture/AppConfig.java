package com.tcg.architecture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tcg.Deck;
import com.tcg.Game;
import com.tcg.Player;
import com.tcg.strategy.PassStrategy;
import com.tcg.strategy.PlayerConsoleStrategy;
import com.tcg.strategy.Strategy;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;

@Configuration
public class AppConfig {

    @Bean
    public PrintSystem printSystem() {
        return new PrintSystem();
    }

    @Bean
    public InputSystem inputSystem() {
        return new InputSystem();
    }

    @Bean
    public Game game() {
        return new Game(printSystem(), player1(), player2());
    }

    @Bean
    public Player player1() {
        Strategy strategy = new PlayerConsoleStrategy(printSystem(), inputSystem());
        return new Player.Builder().setDeck(Deck.createStandardDeck()).setStrategy(strategy).build();
    }

    @Bean
    public Player player2() {
        Strategy strategy = new PassStrategy();
        return new Player.Builder().setDeck(Deck.createStandardDeck()).setStrategy(strategy).build();
    }

}
