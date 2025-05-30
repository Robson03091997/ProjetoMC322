package ambientes.comunicacao;

import ambientes.robos.RoboDesligadoException;

public interface Comunicavel {
    /**
     * Envia uma mensagem para outro robô comunicável
     * @param destinatario robô que receberá a mensagem
     * @param mensagem conteúdo da mensagem
     * @throws RoboDesligadoException se o robô remetente estiver desligado
     * @throws ErroComunicacaoException se houver erro na comunicação
     */
    void enviarMensagem(Comunicavel destinatario, String mensagem) 
        throws RoboDesligadoException, ErroComunicacaoException;

    /**
     * Recebe uma mensagem
     * @param mensagem conteúdo da mensagem recebida
     * @throws RoboDesligadoException se o robô receptor estiver desligado
     * @throws ErroComunicacaoException se houver erro na comunicação
     */
    void receberMensagem(String mensagem) 
        throws RoboDesligadoException, ErroComunicacaoException;
} 