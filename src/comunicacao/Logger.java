package comunicacao;

import robo.AgenteInteligente;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements Comunicador {
    private String nomeArquivo;
    private PrintWriter writer;
    private boolean conectado;
    private AgenteInteligente agente;

    public Logger(String nomeRobo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        this.nomeArquivo = "logs/robo_" + nomeRobo + "_" + sdf.format(new Date()) + ".log";
        this.conectado = false;
    }

    @Override
    public void conectar() {
        try {
            writer = new PrintWriter(new FileWriter(nomeArquivo, true));
            conectado = true;
            enviarMensagem("Logger conectado - " + new Date());
        } catch (IOException e) {
            System.err.println("Erro ao conectar logger: " + e.getMessage());
            conectado = false;
        }
    }

    @Override
    public void desconectar() {
        if (conectado && writer != null) {
            enviarMensagem("Logger desconectado - " + new Date());
            writer.close();
            conectado = false;
        }
    }

    @Override
    public void enviarMensagem(String mensagem) {
        if (conectado && writer != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            writer.println(sdf.format(new Date()) + " - " + mensagem);
            writer.flush();
        }
    }

    @Override
    public String receberMensagem() {
        // Logger não recebe mensagens
        return null;
    }

    @Override
    public boolean estaConectado() {
        return conectado;
    }

    @Override
    public void registrarAgente(AgenteInteligente agente) {
        this.agente = agente;
    }

    public void registrarInicioMissao(String nomeMissao) {
        enviarMensagem("Início da missão: " + nomeMissao);
    }

    public void registrarPosicao(int x, int y) {
        enviarMensagem("Posição atual: (" + x + "," + y + ")");
    }

    public void registrarSensor(String nomeSensor, double valor) {
        enviarMensagem("Sensor " + nomeSensor + ": " + valor);
    }

    public void registrarObstaculo(int x, int y) {
        enviarMensagem("Obstáculo detectado em: (" + x + "," + y + ")");
    }

    public void registrarConclusaoMissao(String nomeMissao) {
        enviarMensagem("Missão concluída: " + nomeMissao);
    }
} 