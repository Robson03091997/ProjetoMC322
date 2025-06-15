package robo;

public interface ControleMovimento {
    void mover(int x, int y);
    boolean verificarMovimento(int x, int y);
    void setRobo(RoboExplorador robo);
} 