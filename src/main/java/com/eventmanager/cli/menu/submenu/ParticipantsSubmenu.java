package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.io.CertificateGenerator;
import com.eventmanager.model.event.Event;
import com.eventmanager.model.participant.External;
import com.eventmanager.model.participant.Participant;
import com.eventmanager.model.participant.Student;
import com.eventmanager.model.participant.Teacher;
import com.eventmanager.service.EventController;
import com.eventmanager.service.ParticipantController;
import com.eventmanager.util.InputValidator;
import com.eventmanager.util.MenuUtils;

public class ParticipantsSubmenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ParticipantController participantController = ParticipantController.getInstance();
    private static final EventController eventController = EventController.getInstance();

    public static void exibirMenu() {
        int opcao;

        do {
            MenuUtils.clearScreen();

            System.out.println("=== PARTICIPANTS SUBMENU ===");
            System.out.println("1. Add Participant");
            System.out.println("2. Search Participant");
            System.out.println("3. Update Participant Data");
            System.out.println("4. Remove Participant Data");
            System.out.println("5. Enroll Participant in an Event");
            System.out.println("6. List all Participants registered in the System");
            System.out.println("7. Generate Participant Certificate");
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
                case 1 -> {
                    MenuUtils.clearScreen();
                    addParticipant();
                    MenuUtils.pause();
                }
                case 2 -> {
                    MenuUtils.clearScreen();
                    searchParticipant();
                    MenuUtils.pause();
                }
                case 3 -> {
                    MenuUtils.clearScreen();
                    updateParticipant();
                    MenuUtils.pause();
                }
                case 4 -> {
                    MenuUtils.clearScreen();
                    removeParticipant();
                    MenuUtils.pause();
                }
                case 5 -> {
                    MenuUtils.clearScreen();
                    enrollParticipant();
                    MenuUtils.pause();
                }
                case 6 -> {
                    MenuUtils.clearScreen();
                    listAllParticipants();
                    MenuUtils.pause();
                }
                case 7 -> {
                    MenuUtils.clearScreen();
                    generateCertificate();
                    MenuUtils.pause();
                }
                case 0 -> MenuUtils.clearScreen();
                default -> {
                    MenuUtils.clearScreen();
                    System.out.println("Invalid option. Try again.");
                    MenuUtils.pause();
                }
            }

        } while (opcao != 0);
    }

    private static void addParticipant() {
        System.out.println("--- ADD PARTICIPANT ---");

        String name;
        do {
            System.out.print("Name: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be empty.");
            }
        } while (name.isBlank());

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!InputValidator.isValidEmail(email)) {
                System.out.println("Invalid email. Try again.");
            }
        } while (!InputValidator.isValidEmail(email));

        String cpf;
        do {
            System.out.print("CPF (format xxx.xxx.xxx-xx): ");
            cpf = scanner.nextLine();
            if (!InputValidator.isValidCPF(cpf)) {
                System.out.println("Invalid CPF. Try again.");
            }
        } while (!InputValidator.isValidCPF(cpf));

        if (participantController.searchByCpf(cpf) != null) {
            System.out.println("Participant with this CPF already exists.");
            return;
        }

        int participantType;
        Participant participant = null;
        do {
            System.out.println("Insert Participant Type");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. External Person\n");
            System.out.print("Select an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Select an option: ");
            }

            participantType = scanner.nextInt();
            scanner.nextLine();

            switch (participantType) {
                case 1 -> {
                    String registrationNumber;
                    do {
                        System.out.print("Registration Number (max 11 digits): ");
                        registrationNumber = scanner.nextLine();
                        if (!InputValidator.isValidRegistrationNumber(registrationNumber)) {
                            System.out.println("Invalid registration number.");
                        }
                    } while (!InputValidator.isValidRegistrationNumber(registrationNumber));

                    String course;
                    do {
                        System.out.print("Course: ");
                        course = scanner.nextLine();
                        if (course.isBlank()) {
                            System.out.println("Course cannot be empty.");
                        }
                    } while (course.isBlank());

                    String institution;
                    do {
                        System.out.print("Institution: ");
                        institution = scanner.nextLine();
                        if (institution.isBlank()) {
                            System.out.println("Institution cannot be empty.");
                        }
                    } while (institution.isBlank());

                    participant = new Student(name, email, cpf, registrationNumber, course, institution);
                }
                case 2 -> {
                    String department;
                    do {
                        System.out.print("Department Name: ");
                        department = scanner.nextLine();
                        if (department.isBlank()) {
                            System.out.println("Department cannot be empty.");
                        }
                    } while (department.isBlank());

                    String institution;
                    do {
                        System.out.print("Institution: ");
                        institution = scanner.nextLine();
                        if (institution.isBlank()) {
                            System.out.println("Institution cannot be empty.");
                        }
                    } while (institution.isBlank());

                    participant = new Teacher(name, email, cpf, department, institution);
                }
                case 3 -> {
                    String profession;
                    do {
                        System.out.print("Profession: ");
                        profession = scanner.nextLine();
                        if (profession.isBlank()) {
                            System.out.println("Profession cannot be empty.");
                        }
                    } while (profession.isBlank());

                    String workPlace;
                    do {
                        System.out.print("Work Place: ");
                        workPlace = scanner.nextLine();
                        if (workPlace.isBlank()) {
                            System.out.println("Work Place cannot be empty.");
                        }
                    } while (workPlace.isBlank());

                    participant = new External(name, email, cpf, profession, workPlace);
                }
                default -> System.out.println("Invalid type. Try again.");
            }

        } while (participantType < 1 || participantType > 3);

        if (participant != null) {
            participantController.addParticipant(participant);
            System.out.println("Participant successfully subscribed.");
        }
    }

    private static void searchParticipant() {
        System.out.print("Enter CPF: ");
        String cpf = scanner.nextLine();

        Participant participant = participantController.searchByCpf(cpf);
        
        if (participant != null)
            System.out.println("Name: " + participant.getName() + " | Email: " + participant.getEmail());
        else
            System.out.println("Participant not found.");
    }

    private static void updateParticipant() {
        System.out.print("Enter CPF of participant: ");
        String cpf = scanner.nextLine();

        if (!InputValidator.isValidCPF(cpf)) {
            System.out.println("Invalid CPF.");
            return;
        }

        Participant participant = participantController.searchByCpf(cpf);
        if (participant == null) {
            System.out.println("Participant not found.");
            return;
        }

        System.out.print("Enter new name of participant: ");
        String name = scanner.nextLine();

        String email;
        do {
            System.out.print("Enter new email of participant: ");
            email = scanner.nextLine();
            if (!InputValidator.isValidEmail(email)) {
                System.out.println("Invalid email. Try again.");
            }
        } while (!InputValidator.isValidEmail(email));

        boolean updated = participantController.updateParticipant(cpf, name, email);
        System.out.println(updated ? "Updated successfully." : "A problem occurred. Please try again.");
    }

    private static void removeParticipant() {
        System.out.print("Enter CPF of participant: ");
        String cpf = scanner.nextLine();

        Participant participant = participantController.searchByCpf(cpf);
        if (participant == null) {
            System.out.println("Participant not found.");
            return;
        }
        
        boolean removed = participantController.removeByCpf(cpf);
        System.out.println(removed ? "Participant data removed successfully." : "A problem occurred. Please try again.");
    }

    private static void enrollParticipant() {
        if (eventController.listAllEvents().isEmpty()) {
            System.out.println("No events available to enroll in.");
            return;
        }

        System.out.print("Enter CPF of participant: ");
        String cpf = scanner.nextLine();
        
        Participant participant = participantController.searchByCpf(cpf);
        if (participant == null) {
            System.out.println("Participant not found.");
            return;
        }
        
        System.out.print("Enter title of event: ");
        String title = scanner.nextLine();
        
        Event event = eventController.searchByTitle(title);
        if (event != null)
            participantController.subscribeInEvent(cpf, event);
        else
            System.out.println("Event not found.");
    }

    private static void listAllParticipants() {
        var list = participantController.listAllParticipants();

        if (list.isEmpty())
            System.out.println("No participants found.");
        else
            list.forEach(p -> System.out.println("- " + p.getName() + " | " + p.getCpf()));
    }

    private static void generateCertificate() {
        var participantAmount = participantController.listAllParticipants();
        if (participantAmount.isEmpty()) {
            System.out.println("There are no participants registered.");
            return;
        }
        
        System.out.print("Enter participant CPF: ");
        String cpf = scanner.nextLine();

        Participant participant = participantController.searchByCpf(cpf);
        if (participant == null) {
            System.out.println("Participant not found.");
            return;
        }

        System.out.print("Enter event title: ");
        String title = scanner.nextLine();
        Event event = eventController.searchByTitle(title);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        if (!participant.getRegisteredEvents().contains(event)) {
            System.out.println("Participant not enrolled in this event.");
            return;
        }

        String fileName = "certificates/Certificate_" + participant.getCpf() + ".pdf";
        CertificateGenerator.generateCertificatePDF(participant, event, fileName);
        System.out.println("Certificate generated at: " + fileName);
    }
}
