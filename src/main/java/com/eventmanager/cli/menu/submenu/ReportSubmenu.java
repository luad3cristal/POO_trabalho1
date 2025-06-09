package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.io.ReportGenerator;
import com.eventmanager.service.EventController;
import com.eventmanager.util.MenuUtils;

public class ReportSubmenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EventController eventController = new EventController();

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
                    reportByType();
                    MenuUtils.pause();
                }
                case 2 -> {
                    MenuUtils.clearScreen();
                    reportByDate();
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

    private static void reportByType() {
        String report = ReportGenerator.generateReportByType(eventController.listAllEvents());
        System.out.println(report);
    }

    private static void reportByDate() {
        String report = ReportGenerator.generateReportByDate(eventController.listAllEvents());
        System.out.println(report);
    }
}
