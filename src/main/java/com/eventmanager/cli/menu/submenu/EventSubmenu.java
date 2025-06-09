package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.model.event.Course;
import com.eventmanager.model.event.Event;
import com.eventmanager.model.event.Fair;
import com.eventmanager.model.event.Lecture;
import com.eventmanager.model.event.Workshop;
import com.eventmanager.service.EventController;
import com.eventmanager.util.DateUtils;
import com.eventmanager.util.MenuUtils;

public class EventSubmenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EventController controller = new EventController();

    public static void exibirMenu() {
        int opcao;

        do {
            MenuUtils.clearScreen();
            System.out.println("=== EVENT SUBMENU ===");
            System.out.println("1. Create Event");
            System.out.println("2. List all Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("0. Back\n");
            System.out.print("Select an option: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> createEvent();
                case 2 -> listEvents();
                case 3 -> updateEvent();
                case 4 -> deleteEvent();
                case 0 -> MenuUtils.clearScreen();
                default -> {
                    System.out.println("Invalid option.");
                    MenuUtils.pause();
                }
            }

        } while (opcao != 0);
    }

    private static void createEvent() {
        MenuUtils.clearScreen();
        System.out.println("--- CREATE EVENT ---");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        String date;
        do {
            System.out.print("Date (dd/MM/yyyy): ");
            date = scanner.nextLine();
        } while (!DateUtils.isValidDate(date));

        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Location (physical, if applicable): ");
        String location = scanner.nextLine();

        System.out.print("Is online? (true/false): ");
        boolean isOnline = scanner.nextBoolean();
        System.out.print("Is in-person? (true/false): ");
        boolean isInPerson = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Event type (1-Lecture, 2-Workshop, 3-Course, 4-Fair): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Event event = switch (type) {
            case 1 -> new Lecture(title, date, location, capacity, "Lecture", isOnline, isInPerson);
            case 2 -> new Workshop(title, date, location, capacity, "Workshop", isOnline, isInPerson);
            case 3 -> new Course(title, date, location, capacity, "Course", isOnline, isInPerson);
            case 4 -> new Fair(title, date, location, capacity, "Fair", isOnline, isInPerson);
            default -> null;
        };

        if (event != null) {
            controller.addEvent(event);
            System.out.println("Event created successfully.");
        } else {
            System.out.println("Invalid type.");
        }

        MenuUtils.pause();
    }

    private static void listEvents() {
        MenuUtils.clearScreen();
        var list = controller.listAllEvents();
        if (list.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (Event e : list) {
                System.out.println("- " + e.getTitle() + " | " + e.getDate() + " | Capacity: " + e.getCapacity());
            }
        }
        MenuUtils.pause();
    }

    private static void updateEvent() {
        MenuUtils.clearScreen();
        System.out.print("Enter title of event to update: ");
        String title = scanner.nextLine();

        System.out.print("New date (dd/MM/yyyy): ");
        String newDate = scanner.nextLine();

        System.out.print("New location: ");
        String newLocation = scanner.nextLine();

        System.out.print("New capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        boolean updated = controller.updateEvent(title, newDate, newLocation, capacity);
        System.out.println(updated ? "Updated successfully." : "Event not found.");
        MenuUtils.pause();
    }

    private static void deleteEvent() {
        MenuUtils.clearScreen();
        System.out.print("Enter title of event to delete: ");
        String title = scanner.nextLine();

        boolean deleted = controller.removeByTitle(title);
        System.out.println(deleted ? "Event removed." : "Event not found.");
        MenuUtils.pause();
    }
}
