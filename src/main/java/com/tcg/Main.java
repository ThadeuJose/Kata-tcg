package com.tcg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcg.architecture.AppConfig;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);) {
            Game game = ctx.getBean(Game.class);
            game.init();
            game.run();
        }
    }
}
