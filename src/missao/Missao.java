package missao;

import robo.AgenteInteligente;
import ambiente.Ambiente;

public interface Missao {
    void executar(AgenteInteligente agente, Ambiente ambiente);
    String getDescricao();
    boolean estaConcluida();
    void iniciar();
    void concluir();
} 