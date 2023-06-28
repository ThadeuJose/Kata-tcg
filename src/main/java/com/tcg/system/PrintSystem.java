package com.tcg.system;

import org.springframework.stereotype.Component;

@Component
public class PrintSystem {
    public void print(String str) {
        System.out.println(str);
    }
}
