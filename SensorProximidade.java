public class SensorProximidade extends Sensor {
    private Robo robo;

    public SensorProximidade(double alcance) {
        super(alcance);
    }

    public SensorProximidade(double alcance, Robo robo) {
        super(alcance);
        this.robo = robo;
    }

    @Override
    public void monitorar() {
        double menorDistancia = -1;

        for (Obstaculo obstaculo : Ambiente.restricoes) {
            double distancia = calcularDistancia(robo, obstaculo);
            if (distancia <= getAlcance()) {
                if (menorDistancia == -1 || distancia < menorDistancia) {
                    menorDistancia = distancia;
                }
            }
        }

        if (menorDistancia != -1) {
            System.out.println("Obstáculo detectado a " + menorDistancia + " unidades de distância.");
        } else {
            System.out.println("Nenhum obstáculo no alcance.");
        }
    }

    private double calcularDistancia(Robo r, Obstaculo o) {
        int x = r.getPosX();
        int y = r.getPosY();
        int z = 0; // Caso o robô esteja sempre no chão

        // Encontra o ponto mais próximo da bounding box do obstáculo
        int closestX = clamp(x, o.getX(), o.getX1());
        int closestY = clamp(y, o.getY(), o.getY1());
        int closestZ = clamp(z, o.getZ(), o.getZ1());

        int dx = x - closestX;
        int dy = y - closestY;
        int dz = z - closestZ;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private int clamp(int valor, int min, int max) {
        return Math.max(min, Math.min(valor, max));
    }

    public void setRobo(Robo robo){
        this.robo = robo;
    }

    public Robo getRobo(){
        return this.robo;
    }
}
