package ambientes.robos;
import java.util.ArrayList;

import ambientes.Ambiente;
import ambientes.sensores.Sensor;

public class Robo {
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ = 0;
    protected String direcao;
    protected int limiteNumSensores;
    private Ambiente ambiente;
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
        this.nome = "Robo" + String.valueOf(Robo.numeroDeRobos+1);
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.direcao = "Não definida";
        Robo.numeroDeRobos++;
    }

    //Declarando métodos get ----------------------------------------------------
    public String getNome(){
        return this.nome;
    }

    public int getPosX() {
        return this.posicaoX;
    }

    public int getPosY() {
        return this.posicaoY;
    }

    public int getPosZ() {
        return this.posicaoZ;
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

    public void setLimiteNumSensores(int limite){
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

    public void setAmbiente(Ambiente ambiente){
        this.ambiente = ambiente;
    }

    public Ambiente getAmbiente(){
        return this.ambiente;
    }


    //Declarando métodos para mover o robo e para exibir a posição atual -------------------------------------------------------------
    public void mover(int X, int Y, int Z){
        this.posicaoX = X;
        this.posicaoY = Y;
    }

    public void exibirPosicao(){
        System.out.println("Posição atual de" + ": " + this.nome + "(" + this.posicaoX + ", " + this.posicaoY + ", " + this.posicaoZ +")");
    }

    //Declarando método de identificação de obstáculos
    protected void identificarObstaculo(){
        ambiente.getFrota().forEach(robo -> System.out.println("Robo: " + robo.getNome() + " posição (" + robo.getPosX() + ", " + robo.getPosY()+")"));
    }
    
    public void adicionarSensor(Sensor sensor){
        if (sensores.size() < limiteNumSensores){
            sensores.add(sensor);
            sensor.setRobo(this);
        }
    }

    protected String ativaInativa(boolean resposta){
        String out = "";
        if (resposta){
            out = "ativa";
        }
        else {
            out = "inativa";
        }
        return out;
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

    public ArrayList<Sensor> getSensores(){
        return this.sensores;
    }

    public String toString(){
        String out = "";
        out += "Robo " + getNome();
        out += "\n--Posicao: (" + getPosX() + ", " + getPosY() + ", " +getPosZ() +"), direcao: " + getDirecao();
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