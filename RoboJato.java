// Classe RoboJato, que herda de RoboAereo
class RoboJato extends RoboAereo {
    private int velocidade;

    protected RoboJato(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int velocidade) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.velocidade = velocidade;
    }

    //Declarando método get para acelerar, ou reduzir a velocidade.
    protected void acelerar(int incremento) {
        this.velocidade += incremento;
        System.out.println(this.nome + " acelerou para " + this.velocidade + " km/h.");
    }

    protected void reduzirVelocidade(int decremento) {
        this.velocidade = Math.max(0, this.velocidade - decremento);
        System.out.println(this.nome + " reduziu a velocidade para " + this.velocidade + " km/h.");
    }
}
