public class RoboAereo extends Robo{

    //Atributos adicionais
    protected int altitude;
    protected int altitudeMaxima;

    //Declarando construtor do RoboAereo
    protected RoboAereo(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao);
        this.altitude = 0;
        this.altitudeMaxima = altitudeMaxima;
    }
    
    
    //Metodos adicionais
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

    @Override
    protected void exibirPosicao() {
        System.out.println(this.nome + " está em: (" + this.posicaoX + ", " + this.posicaoY + ", Altitude: " + this.altitude + ")");
    }
}

