package com.tcg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tcg.strategy.ConsoleStrategy;
import com.tcg.strategy.PassStrategy;
import com.tcg.strategy.Strategy;
import com.tcg.system.InputSystem;
import com.tcg.system.PrintSystem;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public Player player1(PrintSystem printSystem, InputSystem inputSystem) {
        Strategy strategy = new ConsoleStrategy(printSystem, inputSystem);
        Deck standardDeck = DeckFactory.createStandardDeck();
        return new Player.Builder().setPlayerName("Player 1").setDeck(standardDeck).setStrategy(strategy).build();
    }

    @Bean
    public Player player2() {
        Strategy strategy = new PassStrategy();
        Deck standardDeck = DeckFactory.createStandardDeck();
        return new Player.Builder().setPlayerName("Player 2").setDeck(standardDeck).setStrategy(strategy)
                .build();
    }

}
