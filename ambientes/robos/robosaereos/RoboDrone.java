package ambientes.robos.robosaereos;
// Classe RoboDrone, que herda de RoboAereo
public class RoboDrone extends RoboAereo {
    private boolean pairando;

    // Declarando o construtor do RoboDrone
    public RoboDrone(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.pairando = false;
    }

    // Método para ativar o modo de pairar
    public void ativarModoPairar() {
        this.pairando = true;
        System.out.println(this.nome + " está pairando no ar.");
    }

    // Método para desativar o modo de pairar
    public void desativarModoPairar() {
        this.pairando = false;
        System.out.println(this.nome + " saiu do modo de pairar.");
    }

    // Método getter para saber se o drone está pairando
    public boolean isPairando() {
        return this.pairando;
    }

    public String toString(){
        String out = "";
        out += "Robo " + getNome();
        out += "\n--Posicao: (" + getPosX() + ", " + getPosY() + ", " + getAltitude() + ")\n direcao: " + getDirecao() + " altitude máxima: " + getAltitudeMaxima();
        if (isPairando()){
            out += " pairando: sim"; 
        }
        else {
            out += " pairando: não";
        }
        out += "\n--Lista de Sensores ------------------------------------------------------------\n";
        out += "--Número Limite de sensores: " + getLimiteNumSensores() + " Número de Sensores Conectados: " + sensores.size();
        out += "\n";
        for (int i = 0; i < sensores.size(); i++){
            out += sensores.get(i);
            out += "\n";
        }
        return out;
    }
}
