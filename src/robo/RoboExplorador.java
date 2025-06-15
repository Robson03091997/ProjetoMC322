package robo;

import ambiente.Ambiente;
import sensores.Sensor;
import comunicacao.Comunicador;
import missao.Missao;
import java.util.ArrayList;
import java.util.List;
import missao.MissaoExplorar;

public class RoboExplorador extends AgenteInteligente {
    private static final int VELOCIDADE_PADRAO = 1;
    private static final int DISTANCIA_MAXIMA = 10;
    private static final int TEMPO_MAXIMO = 3600;

    private List<String> posicoesVisitadas;

    public RoboExplorador(String nome, Ambiente ambiente,
                         ControleMovimento controleMovimento,
                         GerenciadorSensores gerenciadorSensores,
                         ModuloComunicacao moduloComunicacao) {
        super(nome, ambiente, controleMovimento, gerenciadorSensores, moduloComunicacao);
        this.posicoesVisitadas = new ArrayList<>();
    }

    @Override
    public void inicializar() {
        setPosicao(0, 0);
        comunicador.enviarMensagem("Robô " + nome + " inicializado");
    }

    @Override
    public void executarCiclo() {
        if (emMissao && missaoAtual != null) {
            executarMissao(ambiente);
        }
    }

    @Override
    public boolean verificarCondicoesSeguranca() {
        return gerenciadorSensores.verificarCondicoesSeguranca();
    }

    @Override
    public void executarMissao(Ambiente ambiente) {
        if (missaoAtual instanceof MissaoExplorar) {
            MissaoExplorar missao = (MissaoExplorar) missaoAtual;
            if (!missao.estaConcluida()) {
                missao.executar(this, ambiente);
            } else {
                finalizarMissao();
            }
        }
    }

    private void registrarPosicao() {
        String posicao = posicaoX + "," + posicaoY;
        if (!posicoesVisitadas.contains(posicao)) {
            posicoesVisitadas.add(posicao);
        }
    }

    @Override
    public void setPosicao(int x, int y) {
        super.setPosicao(x, y);
        registrarPosicao();
    }

    public List<String> getPosicoesVisitadas() {
        return new ArrayList<>(posicoesVisitadas);
    }

    public ControleMovimento getControleMovimento() {
        return controleMovimento;
    }

    public void limparHistorico() {
        posicoesVisitadas.clear();
    }
} 