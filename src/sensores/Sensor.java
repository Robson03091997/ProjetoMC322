package sensores;

import ambiente.Ambiente;

public interface Sensor {
    boolean detectarObstaculo(Ambiente ambiente, int x, int y);
    String getTipo();
    void calibrar();
    boolean estaCalibrado();
    boolean estaOperacional();
    void lerDados();
    double getValor();
} 