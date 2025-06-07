package com.eventmanager.menu.submenu;

import java.util.Scanner;

public class ReportSubmenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            clearScreen();

            System.out.println("=== REPORT SUBMENU ===");
            System.out.println("1. Report by Type of Event");
            System.out.println("2. Report by Date");
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
