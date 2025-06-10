package com.eventmanager.cli.menu.submenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.eventmanager.io.ReportGenerator;
import com.eventmanager.model.event.Event;
import com.eventmanager.service.EventController;
import com.eventmanager.util.DateUtils;
import com.eventmanager.util.MenuUtils;

public class ReportSubmenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EventController eventController = EventController.getInstance();


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
        List<Event> events = eventController.listAllEvents();

        if (events.isEmpty()) {
            System.out.println("There are no events registered.");
            return;
        }

        System.out.println("Choose an Event type: \n1-Lecture \n2-Workshop \n3-Course \n4-Fair\nYour option: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        String typeName = switch (type) {
            case 1 -> "Lecture";
            case 2 -> "Workshop";
            case 3 -> "Course";
            case 4 -> "Fair";
            default -> null;
        };

        if (typeName == null) {
            System.out.println("Invalid option.");
            MenuUtils.pause();
            return;
        }

        List<Event> filtered = events.stream()
                .filter(e -> e.getClass().getSimpleName().equalsIgnoreCase(typeName))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No events of type " + typeName + " found.");
        } else {
            String report = ReportGenerator.generateReportByType(filtered);
            System.out.println(report);
        }

        MenuUtils.pause();
    }

    private static void reportByDate() {
        List<Event> events = eventController.listAllEvents();

        if (events.isEmpty()) {
            System.out.println("There are no events registered.");
            return;
        }

        String inputDate;
        LocalDate targetDate = null;

        do {
            System.out.print("Enter the event date (dd/MM/yyyy or dd.MM.yyyy): ");
            inputDate = scanner.nextLine();
            targetDate = DateUtils.parseDateFlexible(inputDate);
        } while (targetDate == null);

        final LocalDate finalDate = targetDate; 

        List<Event> filtered = events.stream()
                .filter(e -> {
                    LocalDate eventDate = DateUtils.parseDateFlexible(e.getDate());
                    return eventDate != null && eventDate.equals(finalDate);
                })
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No events found on " + DateUtils.formatDate(finalDate) + ".");
        } else {
            String report = ReportGenerator.generateReportByDate(filtered);
            System.out.println(report);
        }

        MenuUtils.pause();
    }
}
