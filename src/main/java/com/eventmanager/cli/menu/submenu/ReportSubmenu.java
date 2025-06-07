package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.util.MenuUtils;

public class ReportSubmenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            MenuUtils.clearScreen();

            System.out.println("=== REPORT SUBMENU ===");
            System.out.println("1. Report by Type of Event");
            System.out.println("2. Report by Date");
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
