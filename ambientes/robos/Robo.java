package ambientes.robos;
import java.util.ArrayList;

import ambientes.Ambiente;
import ambientes.ColisaoException;
import ambientes.sensores.Sensor;
import ambientes.entidade.Entidade;
import ambientes.entidade.TipoEntidade;

public abstract class Robo implements Entidade {
    protected String id;  // Identificador único
    protected EstadoRobo estado;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ = 0;
    protected String direcao;
    protected int limiteNumSensores;
    private Ambiente ambiente;
    public static int numeroDeRobos = 0;
    public ArrayList<Sensor> sensores = new ArrayList<>();

    //declarando construtores ------------------------------------------------------
    protected Robo(String id, int posicaoX, int posicaoY, String direcao){
        this.id = id;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.estado = EstadoRobo.DESLIGADO;
        Robo.numeroDeRobos++;
    }

    protected Robo(String id, int posicaoX, int posicaoY, String direcao, int limiteNumSensores){
        this.id = id;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.limiteNumSensores = limiteNumSensores;
        this.estado = EstadoRobo.DESLIGADO;
        Robo.numeroDeRobos++;
    }

    protected Robo(){
        this.id = "Robo" + String.valueOf(Robo.numeroDeRobos+1);
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.direcao = "Não definida";
        this.estado = EstadoRobo.DESLIGADO;
        Robo.numeroDeRobos++;
    }

    // Métodos da interface Entidade
    @Override
    public int getX() {
        return this.posicaoX;
    }

    @Override
    public int getY() {
        return this.posicaoY;
    }

    @Override
    public int getZ() {
        return this.posicaoZ;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.ROBO;
    }

    @Override
    public String getDescricao() {
        return "Robô " + this.id + " na posição (" + this.posicaoX + ", " + this.posicaoY + ", " + this.posicaoZ + ")";
    }

    @Override
    public char getRepresentacao() {
        return 'R';
    }

    // Novos métodos requeridos
    public void moverPara(int x, int y, int z) throws ColisaoException {
        if (estado == EstadoRobo.DESLIGADO) {
            throw new IllegalStateException("Robô desligado não pode se mover");
        }
        
        estado = EstadoRobo.EM_MOVIMENTO;
        try {
            ambiente.moverEntidade(this, x, y, z);
            this.posicaoX = x;
            this.posicaoY = y;
            this.posicaoZ = z;
        } finally {
            estado = EstadoRobo.LIGADO;
        }
    }

    public void ligar() {
        if (estado == EstadoRobo.DESLIGADO) {
            estado = EstadoRobo.LIGADO;
        }
    }

    public void desligar() {
        if (estado != EstadoRobo.EM_MOVIMENTO && estado != EstadoRobo.EM_TAREFA) {
            estado = EstadoRobo.DESLIGADO;
        }
    }

    public EstadoRobo getEstado() {
        return estado;
    }

    // Método abstrato para ações específicas
    public abstract void executarTarefa();

    // Métodos existentes mantidos
    public String getId() {
        return this.id;
    }

    protected String getDirecao() {
        return this.direcao;
    }

    protected void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public void setLimiteNumSensores(int limite) {
        this.limiteNumSensores = limite;
    }

    protected int getLimiteNumSensores() {
        return this.limiteNumSensores;
    }

    protected boolean getSensoresCheio() {
        return this.limiteNumSensores == sensores.size();
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Ambiente getAmbiente() {
        return this.ambiente;
    }

    public void adicionarSensor(Sensor sensor) {
        if (sensores.size() < limiteNumSensores) {
            sensores.add(sensor);
            sensor.setRobo(this);
        }
    }

    protected void removerSensor(Sensor sensor) {
        sensores.remove(sensor);
    }

    public ArrayList<Sensor> getSensores() {
        return this.sensores;
    }

    @Override
    public String toString() {
        String out = "";
        out += "Robô " + getId();
        out += "\n--Estado: " + getEstado();
        out += "\n--Posição: (" + getX() + ", " + getY() + ", " + getZ() + "), direção: " + getDirecao();
        out += "\n--Lista de Sensores ------------------------------------------------------------\n";
        out += "--Número Limite de sensores: " + getLimiteNumSensores() + " Número de Sensores Conectados: " + sensores.size();
        out += "\n";
        for (Sensor sensor : sensores) {
            out += sensor + "\n";
        }
        return out;
    }
}