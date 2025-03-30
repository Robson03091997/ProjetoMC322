// Classe RoboDrone, que herda de RoboAereo
class RoboDrone extends RoboAereo {
    private boolean pairando;

    // Declarando o construtor do RoboDrone
    protected RoboDrone(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.pairando = false;
    }

    // Método para ativar o modo de pairar
    protected void ativarModoPairar() {
        this.pairando = true;
        System.out.println(this.nome + " está pairando no ar.");
    }

    // Método para desativar o modo de pairar
    protected void desativarModoPairar() {
        this.pairando = false;
        System.out.println(this.nome + " saiu do modo de pairar.");
    }

    // Método getter para saber se o drone está pairando
    public boolean isPairando() {
        return this.pairando;
    }
}
