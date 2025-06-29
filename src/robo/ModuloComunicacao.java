package robo;

import comunicacao.Comunicador;

public interface ModuloComunicacao {
    void inicializar();
    void enviarMensagem(String mensagem);
    void registrarComunicador(Comunicador comunicador);
} 