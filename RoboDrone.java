

// Classe RoboDrone, que herda de RoboAereo
class RoboDrone extends RoboAereo {
    private boolean pairando;

    //Declarando o construtor do RoboDrone

    protected RoboDrone(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.pairando = false;
    }

    //Declarando método get para pairar, ou não, no ar
    protected void ativarModoPairar() {
        this.pairando = true;
        System.out.println(this.nome + " está pairando no ar.");
    }

    protected void desativarModoPairar() {
        this.pairando = false;
        System.out.println(this.nome + " saiu do modo de pairar.");
    }
}
