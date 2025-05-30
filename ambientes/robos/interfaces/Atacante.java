package ambientes.robos.interfaces;

@FunctionalInterface
public interface Atacante {
    /**
     * Executa uma ação ofensiva contra um alvo específico
     * @param alvoX coordenada X do alvo
     * @param alvoY coordenada Y do alvo
     * @param alvoZ coordenada Z do alvo
     * @param intensidade nível de força do ataque (0-100)
     * @return true se o ataque foi bem sucedido
     * @throws AcaoNaoPermitidaException se o ataque não for permitido na situação atual
     */
    boolean atacar(int alvoX, int alvoY, int alvoZ, int intensidade) throws AcaoNaoPermitidaException;
} 