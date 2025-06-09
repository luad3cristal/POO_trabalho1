package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.model.participant.*;
import com.eventmanager.model.event.Event;
import com.eventmanager.service.EventController;
import com.eventmanager.service.ParticipantController;
import com.eventmanager.util.InputValidator;
import com.eventmanager.util.MenuUtils;

public class ParticipantsSubmenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ParticipantController participantController = new ParticipantController();
    private static final EventController eventController = new EventController();


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
                    System.out.println("[Simulação] Certificado do participante foi gerado.");
                    MenuUtils.pause();
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

    private static void addParticipant() {
        System.out.println("--- ADD PARTICIPANT ---");

        System.out.println("Name: ");
        String name = scanner.nextLine();

        String email;
        do { 
            System.out.println("Email: ");
            email = scanner.nextLine();
        } while (!InputValidator.isValidEmail(email));
        // precisa comunicar o erro
    
        String cpf;
        do {
            System.out.println("CPF (format xxx.xxx.xxx-xx): ");
            cpf = scanner.nextLine();
        } while (InputValidator.isValidCPF(cpf));
        // validator errado

        int participantType;
        Participant participant = null;
        do { 
            System.out.println("Insert Participant Type");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. External Person\n");
            System.out.print("Select an option: ");

            participantType = scanner.nextInt();
            scanner.nextLine();

        
            switch (participantType) {
                case 1 -> {
                    System.out.println("Registration Number: ");
                    int registrationNumber = scanner.nextInt();
                    scanner.nextLine();
                    // string ou int?
                    
                    System.out.println("Course: ");
                    String course = scanner.nextLine();
    
                    System.out.println("Institution: ");
                    String institution = scanner.nextLine();
    
                    participant = new Student(name, email, cpf, registrationNumber, course, institution);
                }
                case 2 -> {
                    System.out.println("Department Name: ");
                    String department = scanner.nextLine();
    
                    System.out.println("Institution: ");
                    String institution = scanner.nextLine();
    
                    participant = new Teacher(name, email, cpf, department, institution);
                }
                case 3 -> {
                    System.out.println("Profession: ");
                    String profession = scanner.nextLine();
    
                    System.out.println("Work Place: ");
                    String workPlace = scanner.nextLine();
    
                    participant = new External(name, email, cpf, profession, workPlace);
                }
                    
                default -> System.out.println("Invalid type. Try again.");
            }

        } while (participantType > 3 || participantType < 1);

        if (participant != null) {
            participantController.addParticipant(participant);
            System.out.println("Participant sucessfully subscribed.");
        }
    
    }

    private static void searchParticipant() {
        System.out.println("Enter CPF: ");
        String cpf = scanner.nextLine();

        Participant participant = participantController.searchByCpf(cpf);
        
        if (participant != null) 
            System.out.println("Name: " + participant.getName() + " Email: " + participant.getEmail());
        else 
            System.out.println("Participant not found");

    }

    private static void updateParticipant() {
        System.out.println("Enter CPF of participant: ");
        String cpf = scanner.nextLine();
        // validar esse cpf inserido
        
        System.out.println("Enter new name of participant: ");
        String name = scanner.nextLine();
        

        String email;
        do {             
            System.out.println("Enter new email of participant: ");
            email = scanner.nextLine();
        } while (!InputValidator.isValidEmail(email));

        boolean updated = participantController.updateParticipant(cpf, name, email);
        System.out.println(updated ? "Updated sucessfully" : "Participant not found");
    }

    private static void removeParticipant() {
        System.out.println("Enter CPF of participant: ");
        String cpf = scanner.nextLine();
        
        boolean removed = participantController.removeByCpf(cpf);
        System.out.println(removed ? "Participant data removed sucessfully" : "Participant not found");
    }

    private static void enrollParticipant() {
        // checar se tem algum evento


        System.out.println("Enter CPF of participant: ");
        String cpf = scanner.nextLine();
        
        System.out.println("Enter title of event: ");
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
            System.out.println("Event not found.");
        else   
            list.forEach(p -> System.out.println("- " + p.getName() + " | " + p.getCpf() + "\n"));
    }

}
