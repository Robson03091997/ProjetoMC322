package missao;

import robo.AgenteInteligente;
import robo.ControleMovimento;
import ambiente.Ambiente;
import java.util.*;

public class MissaoExplorar implements Missao {
    private String descricao;
    private boolean concluida;
    private List<String> posicoesVisitadas;
    private Set<String> posicoesParaVisitar;
    private int[][] direcoes = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // Baixo, Cima, Direita, Esquerda

    public MissaoExplorar() {
        this.descricao = "Explorar o ambiente e mapear obstáculos";
        this.concluida = false;
        this.posicoesVisitadas = new ArrayList<>();
        this.posicoesParaVisitar = new HashSet<>();
    }

    @Override
    public void executar(AgenteInteligente agente, Ambiente ambiente) {
        if (concluida) return;

        // Inicia a busca recursiva a partir da posição inicial do agente
        buscarTesouro(agente.getPosicaoX(), agente.getPosicaoY(), agente, ambiente);

        // Garante que a missão seja concluída ao final, independentemente do resultado
        if (!concluida) {
            concluir(); // Conclui mesmo se não achar, para parar a execução
        }
    }

    private boolean buscarTesouro(int x, int y, AgenteInteligente agente, Ambiente ambiente) {
        // ---- Casos de Parada da Recursão (Base Cases) ----

        // 1. Se a posição for inválida (fora do mapa ou parede)
        if (!ambiente.posicaoValida(x, y) || ambiente.getElemento(x, y) == '#') {
            return false;
        }

        // 2. Se a posição já foi visitada
        String posicaoAtual = x + "," + y;
        if (posicoesVisitadas.contains(posicaoAtual)) {
            return false;
        }

        // 3. Se encontrou o tesouro
        if (ambiente.getElemento(x, y) == '*') {
            agente.setPosicao(x, y); // Move o agente para a posição final
            concluir();
            return true;
        }

        // ---- Passo Recursivo ----

        // Marca a posição atual como visitada
        posicoesVisitadas.add(posicaoAtual);

        // Atualiza a posição do agente (para visualização do percurso)
        agente.getControleMovimento().mover(x, y);
        agente.setPosicao(x, y);

        // Tenta explorar todas as 4 direções a partir da posição atual
        for (int[] direcao : direcoes) {
            int novoX = x + direcao[0];
            int novoY = y + direcao[1];

            // A mágica da recursão: se a chamada recursiva encontrar o tesouro...
            if (buscarTesouro(novoX, novoY, agente, ambiente)) {
                return true; // ...imediatamente retorna 'true' para a chamada anterior.
            }
        }

        // Se o loop terminar e nenhuma direção levou ao tesouro, esta posição é um beco sem saída.
        // A função retorna 'false', e a execução "volta" para a chamada anterior (backtracking).
        return false;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean estaConcluida() {
        return concluida;
    }

    @Override
    public void iniciar() {
        this.concluida = false;
        this.posicoesVisitadas.clear();
        this.posicoesParaVisitar.clear();
    }

    @Override
    public void concluir() {
        this.concluida = true;
    }

    public List<String> getPosicoesVisitadas() {
        return new ArrayList<>(posicoesVisitadas);
    }
} 