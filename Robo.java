import java.util.ArrayList;

public class Robo {
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;
    protected int limiteNumSensores;
    public static int numeroDeRobos = 0;
    public ArrayList<Sensor> sensores = new ArrayList<>();

    //declarando construtores ------------------------------------------------------
    protected Robo(String nome, int posicaoX, int posicaoY, String direcao){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        Robo.numeroDeRobos++;
    }

    protected Robo(String nome, int posicaoX, int posicaoY, String direcao, int limiteNumSensores){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.limiteNumSensores = limiteNumSensores;
        Robo.numeroDeRobos++;
    }

    protected Robo(){
        this.nome = "Robo" + String.valueOf(Robo.numeroDeRobos);
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.direcao = "Não definida";
        Robo.numeroDeRobos++;
    }

    //Declarando métodos get ----------------------------------------------------
    protected String getNome(){
        return this.nome;
    }

    protected int getPosX() {
        return this.posicaoX;
    }

    protected int getPosY() {
        return this.posicaoY;
    }

    protected String getDirecao() {
        return this.direcao;
    }

    //Declarando métodos set --------------------------------------------------------------------------------
    protected void setNome(String nome){
        this.nome = nome;
    }

    protected void setDirecao(String direcao){
        this.direcao = direcao;
    }

    protected void setLimiteNumSensores(int limite){
        this.limiteNumSensores = limite;
    }

    protected int getLimiteNumSensores(){
        return this.limiteNumSensores;
    }

    protected boolean getSensoresCheio(){
        if (this.limiteNumSensores == sensores.size()){
            return true;
        }
        else{
            return false;
        }
    }


    //Declarando métodos para mover o robo e para exibir a posição atual -------------------------------------------------------------
    protected void mover (int deltaX, int deltaY){
        this.posicaoX = this.posicaoX + deltaX;
        this.posicaoY = this.posicaoY + deltaY;
    }

    protected void exibirPosicao(){
        System.out.println("Posição atual de" + ": " + this.nome + "(" + this.posicaoX + ", " + this.posicaoY + ")");
    }

    //Declarando método de identificação de obstáculos
    protected void identificarObstaculo(){
        Ambiente.hangar.forEach(robo -> System.out.println("Robo: " + robo.getNome() + " posição (" + robo.getPosX() + ", " + robo.getPosY()+")"));
    }
    
    protected void adicionarSensor(Sensor sensor){
        if (sensores.size() < limiteNumSensores){
            sensores.add(sensor);
        }
    }

    protected void removerSensor(Sensor sensor){
        sensores.remove(sensor);
    }

    protected void transfereSensor(Sensor sensor, Robo robo){
        if (!robo.getSensoresCheio()) {
            this.removerSensor(sensor);
            robo.adicionarSensor(sensor);
        }
    }
}