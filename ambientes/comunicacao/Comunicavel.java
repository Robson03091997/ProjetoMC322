package ambientes.comunicacao;

public interface Comunicavel {
    /**
     * Envia uma mensagem para outro robô comunicável
     * @param destinatario robô que receberá a mensagem
     * @param mensagem conteúdo da mensagem
     */
    void enviarMensagem(Comunicavel destinatario, String mensagem);

    /**
     * Recebe uma mensagem
     * @param mensagem conteúdo da mensagem recebida
     */
    void receberMensagem(String mensagem);
} 