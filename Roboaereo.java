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


// Classe RoboDrone, que herda de RoboAereo
class RoboDrone extends RoboAereo {
    private boolean pairando;

    protected RoboDrone(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.pairando = false;
    }

    protected void ativarModoPairar() {
        this.pairando = true;
        System.out.println(this.nome + " está pairando no ar.");
    }

    protected void desativarModoPairar() {
        this.pairando = false;
        System.out.println(this.nome + " saiu do modo de pairar.");
    }
}

// Classe RoboJato, que herda de RoboAereo
class RoboJato extends RoboAereo {
    private int velocidade;

    protected RoboJato(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int velocidade) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.velocidade = velocidade;
    }

    protected void acelerar(int incremento) {
        this.velocidade += incremento;
        System.out.println(this.nome + " acelerou para " + this.velocidade + " km/h.");
    }

    protected void reduzirVelocidade(int decremento) {
        this.velocidade = Math.max(0, this.velocidade - decremento);
        System.out.println(this.nome + " reduziu a velocidade para " + this.velocidade + " km/h.");
    }
}
