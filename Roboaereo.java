public class RoboAereo extends Robo {

    // Atributos adicionais
    protected int altitude;
    protected int altitudeMaxima;

    // Declarando construtor do RoboAereo
    protected RoboAereo(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao);
        this.altitude = 0;
        this.altitudeMaxima = altitudeMaxima;
    }

    // Métodos adicionais para subir e para descer
    protected void subir(int metros) {
        if (this.altitude + metros <= this.altitudeMaxima) {
            this.altitude += metros;
        } else {
            System.out.println("Altitude máxima atingida!");
        }
    }

    protected void descer(int metros) {
        if (this.altitude - metros >= 0) {
            this.altitude -= metros;
        } else {
            System.out.println("O robô já está no solo!");
        }
    }

    // Métodos Getters
    public int getAltitude() {
        return this.altitude;
    }

    public int getAltitudeMaxima() {
        return this.altitudeMaxima;
    }

    // Fazendo override para usar como default a exibição da posição incluindo a altitude
    @Override
    protected void exibirPosicao() {
        System.out.println(this.nome + " está em: (" + this.posicaoX + ", " + this.posicaoY + ", Altitude: " + this.altitude + ")");
    }

    public String toString(){
        String out = "";
        out += "Robo " + getNome();
        out += "\n--Posicao: (" + getPosX() + ", " + getPosY() + ", " + getAltitude() + "), direcao: " + getDirecao() + " altitude máxima: " + getAltitudeMaxima();
        out += "\n--Lista de Sensores ------------------------------------------------------------\n";
        out += "--Número Limite de sensores: " + getLimiteNumSensores() + "Número de Sensores Conectados: " + sensores.size();
        out += "\n";
        for (int i = 0; i < sensores.size(); i++){
            out += sensores.get(i);
            out += "\n";
        }
        return out;
    }
}
