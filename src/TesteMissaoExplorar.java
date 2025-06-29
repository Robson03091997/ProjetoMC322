import ambiente.Ambiente;
import missao.MissaoExplorar;
import robo.AgenteInteligente;
import robo.ControleMovimento;
import robo.GerenciadorSensores;
import robo.ModuloComunicacao;

// Implementação simples de um robô explorador para teste
class RoboExploradorTeste extends AgenteInteligente {
    public RoboExploradorTeste(String nome, Ambiente ambiente, ControleMovimento cm, GerenciadorSensores gs, ModuloComunicacao mc) {
        super(nome, ambiente, cm, gs, mc);
    }
    @Override public void inicializar() {}
    @Override public void executarCiclo() {}
    @Override public boolean verificarCondicoesSeguranca() { return true; }
    @Override public void executarMissao(Ambiente a) {
        if (missaoAtual != null) missaoAtual.executar(this, a);
    }
}

// Implementação mínima dos subsistemas para teste
class ControleMovimentoSimples implements ControleMovimento {
    private RoboExploradorTeste robo;
    @Override public void mover(int x, int y) { System.out.println("Movendo para ("+x+","+y+")"); }
    @Override public boolean verificarMovimento(int x, int y) { return true; }
    @Override public void setRobo(robo.RoboExplorador robo) {}
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

public class TesteMissaoExplorar {
    public static void main(String[] args) {
        // Ambiente 5x5 com um "tesouro" na posição (2,3) e paredes (#)
        Ambiente ambiente = new Ambiente(5, 5);
        // Inicializa o ambiente conforme o cenário de teste
        char[][] mapa = {
            {'.', '.', '.', '.', '.'},
            {'.', '#', '#', '.', '.'},
            {'.', '.', '.', '#', '.'},
            {'.', '.', '*', '.', '.'},
            {'.', '.', '.', '.', '.'}
        };
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                ambiente.setElemento(x, y, mapa[y][x]);
            }
        }
        
        RoboExploradorTeste robo = new RoboExploradorTeste(
            "Explorador1",
            ambiente,
            new ControleMovimentoSimples(),
            new GerenciadorSensoresSimples(),
            new ModuloComunicacaoSimples()
        );
        robo.setPosicao(0, 0);
        MissaoExplorar missao = new MissaoExplorar();
        robo.iniciarMissao(missao);
        robo.executarMissao(ambiente);
        System.out.println("Missão concluída? " + missao.estaConcluida());
        System.out.println("Posições visitadas: " + missao.getPosicoesVisitadas());
    }
} 