package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.model.event.Course;
import com.eventmanager.model.event.Event;
import com.eventmanager.model.event.Fair;
import com.eventmanager.model.event.Lecture;
import com.eventmanager.model.event.Workshop;
import com.eventmanager.service.EventController;
import com.eventmanager.util.DateUtils;
import com.eventmanager.util.InputValidator;
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
            System.out.println("0. Go back to Main Menu\n");
            System.out.print("Select an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid option. Please enter a number.");
                scanner.next();
                System.out.print("Select an option: ");
            }

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
            System.out.print("Date (dd/MM/yyyy or dd.MM.yyyy): ");
            date = scanner.nextLine();
            if (!DateUtils.isValidDate(date)) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (!DateUtils.isValidDate(date));

        String inputCapacity;
        int capacity = -1;
        do {
            System.out.print("Capacity (must be > 0): ");
            inputCapacity = scanner.nextLine();
            if (!InputValidator.isPositiveInt(inputCapacity)) {
                System.out.println("Invalid capacity. Enter a positive number.");
            } else {
                capacity = Integer.parseInt(inputCapacity);
            }
        } while (capacity <= 0);

        String location;
        do {
            System.out.print("Location (can be '-' if not applicable): ");
            location = scanner.nextLine();
            if (location.isBlank()) {
                System.out.println("Location cannot be empty. Use '-' if not applicable.");
            }
        } while (location.isBlank());

        Boolean isOnline = null;
        while (isOnline == null) {
            System.out.print("Is the event online? (true/false): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                isOnline = Boolean.valueOf(input);
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }

        Boolean isInPerson = null;
        while (isInPerson == null) {
            System.out.print("Is the event in-person? (true/false): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                isInPerson = Boolean.valueOf(input);
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }

        int type = -1;
        while (type < 1 || type > 4) {
            System.out.println("Event type (1-Lecture, 2-Workshop, 3-Course, 4-Fair): ");
            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                if (type < 1 || type > 4) System.out.println("Invalid type. Choose 1-4.");
            } else {
                System.out.println("Invalid input. Enter a number from 1 to 4.");
                scanner.next();
            }
        }
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
            System.out.println("Failed to create event. Please try again.");
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

        System.out.print("New date (dd/MM/yyyy or ENTER to keep current): ");
        String newDate = scanner.nextLine();
        if (!newDate.isBlank() && !DateUtils.isValidDate(newDate)) {
            System.out.println("Invalid date. Update aborted.");
            MenuUtils.pause();
            return;
        }

        System.out.print("New location (or ENTER to keep current): ");
        String newLocation = scanner.nextLine();

        String inputCapacity;
        int capacity = -1;
        do {
            System.out.print("New capacity (or ENTER to keep current): ");
            inputCapacity = scanner.nextLine();
            if (inputCapacity.isBlank()) break;
            if (!InputValidator.isPositiveInt(inputCapacity)) {
                System.out.println("Invalid capacity.");
            } else {
                capacity = Integer.parseInt(inputCapacity);
                break;
            }
        } while (true);

        boolean updated = controller.updateEvent(title,
            newDate.isBlank() ? null : newDate,
            newLocation.isBlank() ? null : newLocation,
            capacity);

        System.out.println(updated ? "Updated successfully." : "Event not found.");
        MenuUtils.pause();
    }

    private static void deleteEvent() {
        MenuUtils.clearScreen();
        System.out.print("Enter title of event to delete: ");
        String title = scanner.nextLine();

        if (title.isBlank()) {
            System.out.println("Title cannot be empty.");
        } else {
            boolean deleted = controller.removeByTitle(title);
            System.out.println(deleted ? "Event removed." : "Event not found.");
        }
        MenuUtils.pause();
    }
}
