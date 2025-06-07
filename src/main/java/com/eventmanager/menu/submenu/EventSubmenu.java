package com.eventmanager.menu.submenu;

import java.util.Scanner;

public class EventSubmenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            clearScreen();

            System.out.println("=== EVENT SUBMENU ===");
            System.out.println("1. Create Event");
            System.out.println("2. List all Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("0. Go back to Main Menu");
            System.out.print("Select an option: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 2 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 3 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 4 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 5 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 6 -> {
                    clearScreen();
                    System.out.println("[Simulação]");
                    pause();
                }
                case 0 -> {
                    clearScreen();
                }
                default -> {
                    clearScreen();
                    System.out.println("Opção inválida. Tente novamente.");
                    pause();
                }
            }

        } while (opcao != 0);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
