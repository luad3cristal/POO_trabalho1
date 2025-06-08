package com.eventmanager.util;

import java.util.Scanner;

public class MenuUtils {  
    private static final Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void pause() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }


    public static int readIntInRange(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Try again: ");
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine();
        } while (value < min || value > max);
        return value;
    }

    public static String readNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (!InputValidator.isNonEmpty(input));
        return input;
    }
}
