package main;

import robo.RoboExplorador;
import missao.MissaoExplorar;
import ambiente.Ambiente;
import java.util.List;
import java.util.Scanner;

public class MenuMissao {
    private Scanner scanner;
    private List<RoboExplorador> robos;
    private Ambiente ambiente;

    public MenuMissao(List<RoboExplorador> robos, Ambiente ambiente) {
        this.scanner = new Scanner(System.in);
        this.robos = robos;
        this.ambiente = ambiente;
        this.ambiente.setRobos(robos);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu de Missões ===");
            System.out.println("1. Iniciar Missão de Exploração");
            System.out.println("2. Exibir Ambiente");
            System.out.println("3. Exibir Status dos Robôs");
            System.out.println("4. Controlar Missão do Robô");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    iniciarMissaoExploracao();
                    break;
                case 2:
                    exibirAmbiente();
                    break;
                case 3:
                    exibirStatusRobos();
                    break;
                case 4:
                    controlarMissaoRobo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void controlarMissaoRobo() {
        System.out.println("\n=== Controlar Missão do Robô ===");
        exibirStatusRobos();
        
        System.out.println("\nEscolha o robô (número):");
        for (int i = 0; i < robos.size(); i++) {
            System.out.println((i + 1) + ". " + robos.get(i).getNome());
        }
        
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        if (escolha < 1 || escolha > robos.size()) {
            System.out.println("Robô inválido!");
            return;
        }
        
        RoboExplorador robo = robos.get(escolha - 1);
        
        System.out.println("\nEscolha a ação para o robô " + robo.getNome() + ":");
        System.out.println("1. Iniciar Missão");
        System.out.println("2. Pausar Missão");
        System.out.println("3. Finalizar Missão");
        System.out.println("4. Voltar");
        
        int acao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        switch (acao) {
            case 1:
                if (!robo.estaEmMissao()) {
                    MissaoExplorar missao = new MissaoExplorar();
                    robo.iniciarMissao(missao);
                    robo.inicializar();
                    System.out.println("Missão iniciada para o robô " + robo.getNome());
                } else {
                    System.out.println("Robô já está em missão!");
                }
                break;
            case 2:
                if (robo.estaEmMissao()) {
                    robo.finalizarMissao();
                    System.out.println("Missão pausada para o robô " + robo.getNome());
                } else {
                    System.out.println("Robô não está em missão!");
                }
                break;
            case 3:
                if (robo.estaEmMissao()) {
                    robo.finalizarMissao();
                    robo.limparHistorico();
                    System.out.println("Missão finalizada para o robô " + robo.getNome());
                } else {
                    System.out.println("Robô não está em missão!");
                }
                break;
            case 4:
                return;
            default:
                System.out.println("Ação inválida!");
        }
    }

    private void iniciarMissaoExploracao() {
        System.out.println("\n=== Iniciando Missão de Exploração ===");
        System.out.println("Escolha o robô para iniciar a missão (número):");
        for (int i = 0; i < robos.size(); i++) {
            System.out.println((i + 1) + ". " + robos.get(i).getNome());
        }
        
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        if (escolha < 1 || escolha > robos.size()) {
            System.out.println("Robô inválido!");
            return;
        }
        
        RoboExplorador robo = robos.get(escolha - 1);
        
        if (robo.estaEmMissao()) {
            System.out.println("Robô já está em missão!");
            return;
        }
        
        MissaoExplorar missao = new MissaoExplorar();
        System.out.println("\nIniciando missão para o robô: " + robo.getNome());
        robo.iniciarMissao(missao);
        robo.inicializar();

        while (!missao.estaConcluida()) {
            robo.executarCiclo();
            ambiente.exibir();
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
        }

        robo.finalizarMissao();
        System.out.println("Missão concluída para o robô: " + robo.getNome());
    }

    private void exibirAmbiente() {
        System.out.println("\n=== Estado Atual do Ambiente ===");
        ambiente.exibir();
    }

    private void exibirStatusRobos() {
        System.out.println("\n=== Status dos Robôs ===");
        for (RoboExplorador robo : robos) {
            System.out.println("Robô: " + robo.getNome());
            System.out.println("Posição Atual: (" + robo.getPosicaoX() + "," + robo.getPosicaoY() + ")");
            System.out.println("Estado: " + (robo.estaEmMissao() ? "Em Missão" : "Parado"));
            System.out.println("Posições Visitadas: " + robo.getPosicoesVisitadas().size());
            System.out.println("------------------------");
        }
    }
} 