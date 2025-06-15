package robo;

import ambiente.Ambiente;
import sensores.Sensor;
import comunicacao.Comunicador;
import java.util.ArrayList;
import java.util.List;

public class Robo {
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected Ambiente ambiente;
    protected ControleMovimento controleMovimento;
    protected GerenciadorSensores gerenciadorSensores;
    protected ModuloComunicacao moduloComunicacao;
    protected Comunicador comunicador;
    protected List<Sensor> sensores;

    public Robo(String nome, Ambiente ambiente,
                ControleMovimento controleMovimento,
                GerenciadorSensores gerenciadorSensores,
                ModuloComunicacao moduloComunicacao) {
        this.nome = nome;
        this.ambiente = ambiente;
        this.controleMovimento = controleMovimento;
        this.gerenciadorSensores = gerenciadorSensores;
        this.moduloComunicacao = moduloComunicacao;
        this.sensores = new ArrayList<>();
    }

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

    public void setComunicador(Comunicador comunicador) {
        this.comunicador = comunicador;
    }

    public void adicionarSensor(Sensor sensor) {
        if (sensor != null) {
            sensores.add(sensor);
            gerenciadorSensores.adicionarSensor(sensor);
        }
    }

    public List<Sensor> getSensores() {
        return new ArrayList<>(sensores);
    }

    public GerenciadorSensores getGerenciadorSensores() {
        return gerenciadorSensores;
    }
} 