import ambiente.Ambiente;
import java.util.*;
import missao.MissaoExplorar;
import robo.ControleMovimento;
import robo.GerenciadorSensores;
import robo.ModuloComunicacao;
import robo.RoboExplorador;

class ControleMovimentoSimples implements ControleMovimento {
    private Ambiente ambiente;
    private RoboExplorador robo;
    
    public ControleMovimentoSimples(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
    
    @Override 
    public void mover(int x, int y) { 
        try {
            // Limpa a tela
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Se não conseguir limpar, apenas pula linhas
            for (int i = 0; i < 50; i++) System.out.println();
        }
        
        System.out.println("Movendo para ("+x+","+y+")");
        
        // Atualiza a posição do robô no ambiente para exibição
        if (robo != null) {
            robo.setPosicao(x, y);
            // Exibe o ambiente atualizado
            ambiente.exibir();
        }
        
        // Timer de 0.5 segundos
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override 
    public boolean verificarMovimento(int x, int y) { 
        return ambiente.posicaoValida(x, y) && ambiente.getElemento(x, y) != '#';
    }
    
    @Override 
    public void setRobo(RoboExplorador robo) {
        this.robo = robo;
    }
}
class GerenciadorSensoresSimples implements GerenciadorSensores {
    @Override public void adicionarSensor(sensores.Sensor s) {}
    @Override public void removerSensor(sensores.Sensor s) {}
    @Override public java.util.List<sensores.Sensor> getSensores() { return new java.util.ArrayList<>(); }
    @Override public void lerSensores() {}
    @Override public boolean verificarCondicoesSeguranca() { return true; }
    @Override public boolean verificarObstaculos(int x, int y) { return false; }
}
class ModuloComunicacaoSimples implements ModuloComunicacao {
    @Override public void inicializar() {}
    @Override public void enviarMensagem(String m) {}
    @Override public void registrarComunicador(comunicacao.Comunicador c) {}
}

public class InterfaceSimulador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ambiente ambiente = new Ambiente(8, 8); // Tamanho padrão
        List<RoboExplorador> robos = new ArrayList<>();
        ambiente.setRobos(robos); // Para exibição
        while (true) {
            System.out.println("\n--- SIMULADOR DE AMBIENTE ---");
            System.out.println("1. Exibir ambiente");
            System.out.println("2. Inserir obstáculo");
            System.out.println("3. Inserir robô");
            System.out.println("4. Inserir alvo");
            System.out.println("5. Mover robô manualmente");
            System.out.println("6. Atribuir missão e executar");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();
            if (op == 1) {
                ambiente.setRobos(robos);
                ambiente.exibir();
            } else if (op == 2) {
                System.out.print("Posição X do obstáculo: "); int x = sc.nextInt();
                System.out.print("Posição Y do obstáculo: "); int y = sc.nextInt();
                ambiente.adicionarObstaculo(x, y);
                System.out.println("Obstáculo inserido!");
            } else if (op == 3) {
                System.out.print("Nome do robô: "); String nome = sc.next();
                System.out.print("Posição X do robô: "); int x = sc.nextInt();
                System.out.print("Posição Y do robô: "); int y = sc.nextInt();
                ControleMovimentoSimples cm = new ControleMovimentoSimples(ambiente);
                RoboExplorador robo = new RoboExplorador(nome, ambiente, cm, new GerenciadorSensoresSimples(), new ModuloComunicacaoSimples());
                cm.setRobo(robo);
                robo.setPosicao(x, y);
                robos.add(robo);
                System.out.println("Robô inserido!");
            } else if (op == 4) {
                System.out.print("Posição X do alvo: "); int x = sc.nextInt();
                System.out.print("Posição Y do alvo: "); int y = sc.nextInt();
                ambiente.setElemento(x, y, '*');
                System.out.println("Alvo inserido!");
            } else if (op == 5) {
                if (robos.isEmpty()) { System.out.println("Nenhum robô disponível."); continue; }
                for (int i = 0; i < robos.size(); i++)
                    System.out.println(i+" - "+robos.get(i).getNome()+" ("+robos.get(i).getPosicaoX()+","+robos.get(i).getPosicaoY()+")");
                System.out.print("Escolha o robô: "); int idx = sc.nextInt();
                if (idx < 0 || idx >= robos.size()) { System.out.println("Índice inválido."); continue; }
                System.out.print("Nova posição X: "); int x = sc.nextInt();
                System.out.print("Nova posição Y: "); int y = sc.nextInt();
                robos.get(idx).setPosicao(x, y);
                System.out.println("Robô movido!");
            } else if (op == 6) {
                if (robos.isEmpty()) { System.out.println("Nenhum robô disponível."); continue; }
                for (int i = 0; i < robos.size(); i++)
                    System.out.println(i+" - "+robos.get(i).getNome()+" ("+robos.get(i).getPosicaoX()+","+robos.get(i).getPosicaoY()+")");
                System.out.print("Escolha o robô: "); int idx = sc.nextInt();
                if (idx < 0 || idx >= robos.size()) { System.out.println("Índice inválido."); continue; }
                
                System.out.println("Iniciando missão de exploração...");
                System.out.println("Pressione Enter para começar...");
                sc.nextLine(); // Consome o \n anterior
                sc.nextLine(); // Aguarda Enter
                
                MissaoExplorar missao = new MissaoExplorar();
                robos.get(idx).iniciarMissao(missao);
                robos.get(idx).executarMissao(ambiente);
                
                System.out.println("\n=== RESULTADO DA MISSÃO ===");
                System.out.println("Missão concluída? " + missao.estaConcluida());
                System.out.println("Posições visitadas: " + missao.getPosicoesVisitadas());
                System.out.println("Pressione Enter para continuar...");
                sc.nextLine();
            } else if (op == 7) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
} 