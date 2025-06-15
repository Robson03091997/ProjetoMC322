package robo;

import sensores.Sensor;
import java.util.List;
import java.util.ArrayList;

public interface GerenciadorSensores {
    void adicionarSensor(Sensor sensor);
    void removerSensor(Sensor sensor);
    List<Sensor> getSensores();
    void lerSensores();
    boolean verificarCondicoesSeguranca();
    boolean verificarObstaculos(int x, int y);
} 