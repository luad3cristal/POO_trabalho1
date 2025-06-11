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
    private static final EventController eventController = EventController.getInstance();

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

        String title;
        do {
            System.out.print("Title: ");
            title = scanner.nextLine();
        } while (title.isBlank());

        String date;
        do {
            System.out.print("Date: ");
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

        String onlineLink = "-";
        if (isOnline) {
            do {
                System.out.print("What is the link? (Use '-' if it is unknown): ");
                onlineLink = scanner.nextLine();
                if (!InputValidator.isValidURL(onlineLink)) {
                    System.out.println("Please enter a proper link. Use '-' if it is unknown.");
                    onlineLink = null;
                }
            } while (onlineLink == null);
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

        String location = "-";
        if (isInPerson) {
            do {
                System.out.print("Location (can be '-' if it is unknown): ");
                location = scanner.nextLine();
                if (location.isBlank()) {
                    System.out.println("Location cannot be empty. Use '-' if it is unknown.");
                }
            } while (location.isBlank());
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
            case 1 -> new Lecture(title, date, location, onlineLink, capacity, "Lecture", isOnline, isInPerson);
            case 2 -> new Workshop(title, date, location, onlineLink, capacity, "Workshop", isOnline, isInPerson);
            case 3 -> new Course(title, date, location, onlineLink, capacity, "Course", isOnline, isInPerson);
            case 4 -> new Fair(title, date, location, onlineLink, capacity, "Fair", isOnline, isInPerson);
            default -> null;
        };

        if (event != null) {
            eventController.addEvent(event);
            System.out.println("Event created successfully.");
        } else {
            System.out.println("Failed to create event. Please try again.");
        }

        MenuUtils.pause();
    }

    private static void listEvents() {
        MenuUtils.clearScreen();
        var list = eventController.listAllEvents();
        if (list.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (Event e : list) {
                System.out.println(e.getEventDescription() + "\n");
            }
        }
        MenuUtils.pause();
    }

    private static void updateEvent() {
        MenuUtils.clearScreen();

        String title;
        do {
            System.out.print("Enter title of event to update: ");
            title = scanner.nextLine();
        } while (title.isBlank());

        System.out.print("New date (or ENTER to keep current): ");
        String newDate = scanner.nextLine();
        if (!newDate.isBlank() && !DateUtils.isValidDate(newDate)) {
            System.out.println("Invalid date. Update aborted.");
            MenuUtils.pause();
            return;
        }

        System.out.print("New location (or ENTER to keep current): ");
        String newLocation = scanner.nextLine();

        System.out.print("New online link (or ENTER to keep current): ");
        String newLink = scanner.nextLine();
        if (!newLink.isBlank() && !InputValidator.isValidURL(newLink)) {
            System.out.println("Invalid link. Update aborted.");
            MenuUtils.pause();
            return;
        }

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

        boolean updated = eventController.updateEvent(
            title,
            newDate.isBlank() ? null : newDate,
            newLocation.isBlank() ? null : newLocation,
            capacity,
            newLink.isBlank() ? null : newLink
        );

        System.out.println(updated ? "Updated successfully." : "Event not found.");
        MenuUtils.pause();
    }

    private static void deleteEvent() {
        MenuUtils.clearScreen();

        String title;
        do {
            System.out.print("Enter title of event to delete: ");
            title = scanner.nextLine();
        } while (title.isBlank());

        boolean deleted = eventController.removeByTitle(title);
        System.out.println(deleted ? "Event removed." : "Event not found.");
        MenuUtils.pause();
    }
}
