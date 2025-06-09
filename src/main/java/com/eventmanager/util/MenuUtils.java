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
}
