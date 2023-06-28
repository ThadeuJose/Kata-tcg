package com.tcg.system;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class InputSystem {
    private Scanner scanner;

    public InputSystem() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

}
