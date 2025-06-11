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

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Select an option: ");
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    MenuUtils.clearScreen();
                    reportByType();
                }
                case 2 -> {
                    MenuUtils.clearScreen();
                    reportByDate();
                }
                case 0 -> {
                    MenuUtils.clearScreen();
                }
                default -> {
                    MenuUtils.clearScreen();
                    System.out.println("Invalid option. Try again.");
                    MenuUtils.pause();
                }
            }

        } while (opcao != 0);
    }

    private static void reportByType() {
        List<Event> events = eventController.listAllEvents();

        if (events.isEmpty()) {
            System.out.println("There are no events registered.");
            MenuUtils.pause();
            return;
        }

        int type = -1;
        while (type < 1 || type > 4) {
            System.out.println("Choose an Event type: \n1-Lecture \n2-Workshop \n3-Course \n4-Fair\nYour option: ");
            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                if (type < 1 || type > 4) {
                    System.out.println("Invalid option. Please choose between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.nextLine();

        String typeName = switch (type) {
            case 1 -> "Lecture";
            case 2 -> "Workshop";
            case 3 -> "Course";
            case 4 -> "Fair";
            default -> null;
        };

        List<Event> filtered = events.stream()
                .filter(e -> e.getClass().getSimpleName().equalsIgnoreCase(typeName))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("\nNo events of type " + typeName + " found.");
        } else {
            MenuUtils.clearScreen();
            String report = ReportGenerator.generateReportByType(filtered);
            System.out.println(report);
        }

        MenuUtils.pause();
    }

    private static void reportByDate() {
        List<Event> events = eventController.listAllEvents();

        if (events.isEmpty()) {
            System.out.println("There are no events registered.");
            MenuUtils.pause();
            return;
        }

        String inputDate;
        LocalDate targetDate = null;

        do {
            System.out.print("Enter the event date (dd/MM/yyyy or dd.MM.yyyy): ");
            inputDate = scanner.nextLine();
            targetDate = DateUtils.parseDateFlexible(inputDate);
            if (targetDate == null) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (targetDate == null);

        final LocalDate finalDate = targetDate;

        List<Event> filtered = events.stream()
                .filter(e -> {
                    LocalDate eventDate = DateUtils.parseDateFlexible(e.getDate());
                    return eventDate != null && eventDate.equals(finalDate);
                })
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("\nNo events found on " + DateUtils.formatDate(finalDate) + ".");
        } else {
            MenuUtils.clearScreen();
            String report = ReportGenerator.generateReportByDate(filtered);
            System.out.println(report);
        }

        MenuUtils.pause();
    }
}
