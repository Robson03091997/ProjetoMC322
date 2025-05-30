package ambientes;
import java.util.ArrayList;
import ambientes.entidade.*;
import ambientes.obstaculos.*;
import ambientes.robos.*;
import ambientes.sensores.*;

public class Ambiente
{
    private final int largura;
    private final int profundidade;
    private int altura;
    private String nome;
    private ArrayList<Robo> frota = new ArrayList<>();
    private ArrayList<Obstaculo> restricoes = new ArrayList<>();
    private ArrayList<Entidade> entidades = new ArrayList<>();
    private TipoEntidade[][][] mapa;
    private TipoEntidade vazio = TipoEntidade.VAZIO;

    //construtor
    
    public Ambiente(String nome, int largura, int profundidade, int altura){
        this.largura = largura;
        this.profundidade = profundidade;
        this.altura = altura;
        this.nome = nome;
        this.inicializaMapa();
    }

    private void inicializaMapa(){
        mapa = new TipoEntidade[largura][profundidade][altura];
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < profundidade; y++) {
                for (int z = 0; z < altura; z++) {
                    mapa[x][y][z] = this.vazio;
                }
            }
        }
        for (int i = 0; i < entidades.size(); i++){
            mapa[entidades.get(i).getX()][entidades.get(i).getY()][entidades.get(i).getZ()] = entidades.get(i).getTipo();
        }
    }

    //metodos

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public int getLargura(){
        return this.largura;
    }

    public int getProfundidade(){
        return this.profundidade;
    }

    public int getAltura(){
        return this.altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public ArrayList<Robo> getFrota(){
        return this.frota;
    }

    public ArrayList<Obstaculo> getRestricoes(){
        return this.restricoes;
    }

    public boolean dentroDosLimites(int x, int y, int z) throws ColisaoException {
        if ((x >= 0) && (x < largura) && (y >= 0) && (y < profundidade) && (z >= 0) && (z < altura)) {
            return true;
        } else {
            throw new ColisaoException("Posição (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente");
        }
    }

    public boolean estaOcupado(int x, int y, int z) throws ColisaoException {
        if (!dentroDosLimites(x, y, z)) {
            throw new ColisaoException("Posição fora dos limites");
        }
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws ColisaoException {
        if (!dentroDosLimites(novoX, novoY, novoZ)) {
            throw new ColisaoException("Nova posição fora dos limites");
        }
        
        if (estaOcupado(novoX, novoY, novoZ)) {
            throw new ColisaoException("Posição de destino já está ocupada");
        }

        // Limpa posição atual
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
        
        // Atualiza a posição se a entidade for um robô
        if (e instanceof Robo) {
            ((Robo) e).mover(novoX, novoY, novoZ);
        }
        
        // Atualiza o mapa
        mapa[novoX][novoY][novoZ] = e.getTipo();
    }

    public void executarSensores() {
        for (Robo robo : frota) {
            for (Sensor sensor : robo.getSensores()) {
                sensor.monitorar();
            }
        }
    }

    public void verificarColisoes() throws ColisaoException {
        for (Entidade e1 : entidades) {
            for (Entidade e2 : entidades) {
                if (e1 != e2 && 
                    e1.getX() == e2.getX() && 
                    e1.getY() == e2.getY() && 
                    e1.getZ() == e2.getZ()) {
                    throw new ColisaoException(
                        "Colisão detectada entre " + e1.getDescricao() + " e " + e2.getDescricao()
                    );
                }
            }
        }
    }

    public void visualizarAmbiente() {
        System.out.println("Ambiente " + nome + " (" + largura + "x" + profundidade + ")");
        System.out.println("Legenda: [.] Vazio, [R] Robô, [O] Obstáculo");
        
        for (int y = profundidade - 1; y >= 0; y--) {
            for (int x = 0; x < largura; x++) {
                boolean posicaoVazia = true;
                // Procura entidade em qualquer altura nesta posição x,y
                for (int z = 0; z < altura; z++) {
                    if (mapa[x][y][z] != TipoEntidade.VAZIO) {
                        // Encontra a entidade correspondente para pegar sua representação
                        for (Entidade e : entidades) {
                            if (e.getX() == x && e.getY() == y && e.getZ() == z) {
                                System.out.print("[" + e.getRepresentacao() + "]");
                                posicaoVazia = false;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (posicaoVazia) {
                    System.out.print("[.]");
                }
            }
            System.out.println();
        }
    }

    public void adicionarRobo(Robo robo){
        frota.add(robo);
        robo.setAmbiente(this);
    }

    public void removerRobo(Robo robo){
        frota.remove(robo);
    }

    public void removerRobo(String robo){
        int verificaRemocao = 0;
        for (int i = 0; i < frota.size(); i++){
            if (frota.get(i).getNome().equals(robo)){
                System.out.println("Robo "+frota.get(i).getNome()+" removido.");
                frota.remove(i);
                verificaRemocao = 1;
                break;
            }
        }
        if (verificaRemocao == 0){
            System.out.println("Robo a ser removido não foi encontrado.");
        }
    }


    public void adicionarObstaculo(int x, int y, int z, int largura, int profundidade, int altura, TipoObstaculo tipo){
        Obstaculo obstaculo = new Obstaculo(x, y, z, largura, profundidade, altura, tipo);
        restricoes.add(obstaculo);
    }

    public void removerObstaculo(Obstaculo obstaculo){
        restricoes.remove(obstaculo);
    }

    public void removerObstaculo(int obstaculo){
        if (restricoes.size() > obstaculo && obstaculo > -1){
            restricoes.remove(obstaculo);
        }
        else{
            System.out.println("Não há esse obstáculo no ambiente.");
        }
    }

    public void adicionarEntidade(Entidade e){
        entidades.add(e);
    }

    public void removerEntidade(Entidade entidade){
        this.mapa[entidade.getX()][entidade.getY()][entidade.getZ()] = this.vazio;
        entidades.remove(entidade);
    }

    public void removerEntidade(int entidade){
        if (entidades.size() > entidade && entidade > -1){
            this.mapa[entidades.get(entidade).getX()][entidades.get(entidade).getY()][entidades.get(entidade).getZ()] = this.vazio;
            restricoes.remove(entidade);
        }
        else{
            System.out.println("Não há essa entidade no ambiente.");
        }
    }

    public String toString(){
        String out = "";
        out += "Ambiente " + getNome();
        out += "\nprofundidade: " + getProfundidade() + " Largura: " + getLargura() + " Altura: " + getAltura();
        out += "\nLista de Robôs presentes no ambiente --------------------------------------------\n";
        for (int i = 0; i < frota.size(); i++){
            out += "-"+frota.get(i) + "\n";
        }
        out += "Lista de Obstáculos presentes no ambiente -----------------------------------------\n";
        for (int i = 0; i < restricoes.size(); i++){
            out += "-"+restricoes.get(i) + "\n";
        }
        return out;
    }

    // Método de exemplo para verificar tipo de entidade em uma posição
    public boolean isRoboNaPosicao(int x, int y, int z) {
        if (!dentroDosLimites(x, y, z)) {
            return false;
        }
        Entidade e = entidades.stream()
            .filter(ent -> ent.getX() == x && ent.getY() == y && ent.getZ() == z)
            .findFirst()
            .orElse(null);
        return e != null && e.getTipo() == TipoEntidade.ROBO;
    }
}
