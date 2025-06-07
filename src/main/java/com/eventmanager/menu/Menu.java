package com.eventmanager.menu;

import java.util.Scanner;

import com.eventmanager.menu.submenu.EventSubmenu;
import com.eventmanager.menu.submenu.ParticipantsSubmenu;
import com.eventmanager.menu.submenu.ReportSubmenu;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            clearScreen();
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Access Event Menu");
            System.out.println("2. Access Participants Menu");
            System.out.println("3. Access Report Menu");
            System.out.println("0. Leave");
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
                    System.out.println("Encerrando o programa...");
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }

        } while (opcao != 0);
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
