package com.eventmanager.cli.menu;

import java.util.Scanner;

import com.eventmanager.util.MenuUtils;

import com.eventmanager.cli.menu.submenu.EventSubmenu;
import com.eventmanager.cli.menu.submenu.ParticipantsSubmenu;
import com.eventmanager.cli.menu.submenu.ReportSubmenu;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            MenuUtils.clearScreen();
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Access Event Menu");
            System.out.println("2. Access Participants Menu");
            System.out.println("3. Access Report Menu");
            System.out.println("0. Leave\n");
            System.out.print("Select an option: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    EventSubmenu.exibirMenu();
                }
                case 2 -> {
                    ParticipantsSubmenu.exibirMenu();
                }
                case 3 -> {
                    ReportSubmenu.exibirMenu();
                }
                case 0 -> {
                    MenuUtils.clearScreen();
                    System.out.println("Encerrando o programa...");
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }

        } while (opcao != 0);
    }
}
