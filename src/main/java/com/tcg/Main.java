package com.tcg;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);) {
            System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));
            Game game = ctx.getBean(Game.class);
            game.init();
            game.run();
        }
    }
}
