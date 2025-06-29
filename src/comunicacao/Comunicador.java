package comunicacao;

import robo.AgenteInteligente;

public interface Comunicador {
    void conectar();
    void desconectar();
    void enviarMensagem(String mensagem);
    String receberMensagem();
    boolean estaConectado();
    void registrarAgente(AgenteInteligente agente);
} 