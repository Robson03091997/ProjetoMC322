// Classe RoboJato, que herda de RoboAereo
class RoboJato extends RoboAereo {
    private int velocidade;

    // Declarando o construtor
    protected RoboJato(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int velocidade) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima);
        this.velocidade = velocidade;
    }

    // Método para acelerar
    protected void acelerar(int incremento) {
        this.velocidade += incremento;
        System.out.println(this.nome + " acelerou para " + this.velocidade + " km/h.");
    }

    // Método para reduzir a velocidade
    protected void reduzirVelocidade(int decremento) {
        this.velocidade = Math.max(0, this.velocidade - decremento);
        System.out.println(this.nome + " reduziu a velocidade para " + this.velocidade + " km/h.");
    }

    // Método getter para a velocidade
    public int getVelocidade() {
        return this.velocidade;
    }

    public String toString(){
        String out = "";
        out += "Robo " + getNome();
        out += "\n--Posicao: (" + getPosX() + ", " + getPosY() + ", " + getAltitude() + ") direcao: " + getDirecao() + " altitude máxima: " + getAltitudeMaxima() + " velocidade: " + getVelocidade();
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