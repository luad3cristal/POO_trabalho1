package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.util.MenuUtils;

public class EventSubmenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            MenuUtils.clearScreen();

            System.out.println("=== EVENT SUBMENU ===");
            System.out.println("1. Create Event");
            System.out.println("2. List all Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("0. Go back to Main Menu\n");
            System.out.print("Select an option: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 2 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 3 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 4 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 5 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 6 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação]");
                    MenuUtils.pause();
                }
                case 0 -> {
                    MenuUtils.clearScreen();
                }
                default -> {
                    MenuUtils.clearScreen();
                    System.out.println("Opção inválida. Tente novamente.");
                    MenuUtils.pause();
                }
            }

        } while (opcao != 0);
    }
}
