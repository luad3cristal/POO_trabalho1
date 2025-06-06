package com.eventmanager.menu;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Cadastrar Participante");
            System.out.println("3. Inscrever Participante em Evento");
            System.out.println("4. Gerar Certificados");
            System.out.println("5. Gerar Relatório de Eventos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    cadastrarParticipante();
                    break;
                case 3:
                    inscreverParticipante();
                    break;
                case 4:
                    gerarCertificados();
                    break;
                case 5:
                    gerarRelatorio();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    // Métodos auxiliares de cada opção (a serem implementados)
    private static void cadastrarEvento() {
        System.out.println("📌 [Simulação] Evento cadastrado.");
        // Aqui vai a lógica real no futuro
    }

    private static void cadastrarParticipante() {
        System.out.println("📌 [Simulação] Participante cadastrado.");
    }

    private static void inscreverParticipante() {
        System.out.println("📌 [Simulação] Participante inscrito.");
    }

    private static void gerarCertificados() {
        System.out.println("📌 [Simulação] Certificados gerados.");
    }

    private static void gerarRelatorio() {
        System.out.println("📌 [Simulação] Relatório exibido.");
    }
}
