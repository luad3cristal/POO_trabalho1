package com.eventmanager.cli.menu.submenu;

import java.util.Scanner;

import com.eventmanager.util.MenuUtils;

public class ParticipantsSubmenu {

    private static final Scanner scanner = new Scanner(System.in);

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
                    System.out.println("[Simulação] Participante adicionado.");
                    MenuUtils.pause();
                }
                case 2 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação] Participante buscado.");
                    MenuUtils.pause();
                }
                case 3 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação] Participante atualizado.");
                    MenuUtils.pause();
                }
                case 4 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação] Participante removido.");
                    MenuUtils.pause();
                }
                case 5 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação] Participante inscrito em evento.");
                    MenuUtils.pause();
                }
                case 6 -> {
                    MenuUtils.clearScreen();
                    System.out.println("[Simulação] Todos os participantes foram listados.");
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
                    System.out.println("Opção inválida. Tente novamente.");
                    MenuUtils.pause();
                }
            }

        } while (opcao != 0);
    }


}
