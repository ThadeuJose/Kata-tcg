package com.tcg.architecture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tcg.Game;
import com.tcg.system.PrintSystem;

@Configuration
public class AppConfig {

    @Bean
    public PrintSystem printSystem() {
        return new PrintSystem();
    }

    @Bean
    public Game game() {
        return new Game(printSystem());
    }

}
