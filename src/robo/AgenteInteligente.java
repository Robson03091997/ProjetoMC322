package robo;

import ambiente.Ambiente;
import sensores.Sensor;
import comunicacao.Comunicador;
import missao.Missao;
import java.util.ArrayList;
import java.util.List;

public abstract class AgenteInteligente {
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected Ambiente ambiente;
    protected ControleMovimento controleMovimento;
    protected GerenciadorSensores gerenciadorSensores;
    protected ModuloComunicacao moduloComunicacao;
    protected Comunicador comunicador;
    protected boolean emMissao;
    protected Missao missaoAtual;

    public AgenteInteligente(String nome, Ambiente ambiente,
                            ControleMovimento controleMovimento,
                            GerenciadorSensores gerenciadorSensores,
                            ModuloComunicacao moduloComunicacao) {
        this.nome = nome;
        this.ambiente = ambiente;
        this.controleMovimento = controleMovimento;
        this.gerenciadorSensores = gerenciadorSensores;
        this.moduloComunicacao = moduloComunicacao;
        this.emMissao = false;
        this.missaoAtual = null;
    }

    public abstract void inicializar();
    public abstract void executarCiclo();
    public abstract boolean verificarCondicoesSeguranca();
    public abstract void executarMissao(Ambiente a);

    public void setPosicao(int x, int y) {
        if (ambiente.posicaoValida(x, y)) {
            posicaoX = x;
            posicaoY = y;
        }
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public String getNome() {
        return nome;
    }

    public void iniciarMissao(Missao missao) {
        this.missaoAtual = missao;
        this.emMissao = true;
    }

    public void finalizarMissao() {
        this.missaoAtual = null;
        this.emMissao = false;
    }

    public boolean estaEmMissao() {
        return emMissao;
    }

    public Missao getMissaoAtual() {
        return missaoAtual;
    }

    public void setComunicador(Comunicador comunicador) {
        this.comunicador = comunicador;
    }

    public void lerSensores() {
        for (Sensor sensor : gerenciadorSensores.getSensores()) {
            if (sensor.estaOperacional()) {
                sensor.lerDados();
            }
        }
    }

    public ControleMovimento getControleMovimento() {
        return controleMovimento;
    }
} 