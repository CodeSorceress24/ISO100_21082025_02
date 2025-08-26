package view;

import java.util.Scanner;
import controller.KillController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KillController killController = new KillController();
        int opcao;
        
        do {
            System.out.println("\n=== GERENCIADOR DE PROCESSOS ===");
            System.out.println("1. Listar processos ativos");
            System.out.println("2. Matar processo por PID");
            System.out.println("3. Matar processo por Nome");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
            
            switch (opcao) {
                case 1:
                    killController.listaProcessos();
                    break;
                    
                case 2:
                    System.out.print("Digite o PID do processo a ser finalizado: ");
                    try {
                        int pid = Integer.parseInt(scanner.nextLine());
                        killController.mataPid(pid);
                    } catch (NumberFormatException e) {
                        System.out.println("PID inválido! Digite apenas números.");
                    }
                    break;
                    
                case 3:
                    System.out.print("Digite o nome do processo a ser finalizado: ");
                    String nomeProcesso = scanner.nextLine().trim();
                    if (!nomeProcesso.isEmpty()) {
                        killController.mataNome(nomeProcesso);
                    } else {
                        System.out.println("Nome do processo não pode estar vazio!");
                    }
                    break;
                    
                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
}