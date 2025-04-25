import java.util.ArrayList;

public class Ambiente
{
    private final int largura;
    private final int comprimento;
    private int altura;
    private String nome;
    public static ArrayList<Robo> frota = new ArrayList<>();
    public static ArrayList<Obstaculo> restricoes = new ArrayList<>();

    //construtor
    
    public Ambiente(String nome, int largura, int comprimento, int altura){
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
        this.nome = nome;
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

    public int getComprimento(){
        return this.comprimento;
    }

    public int getAltura(){
        return this.altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public boolean dentroDosLimites(int x, int y, int z){
        if( (x >= 0) && (x < largura) && (y >= 0) && (y < comprimento) && (z < altura)){

            return true;            
        }
        else{

            System.out.println("fora dos limites");

            return false;
        }
    }

    public boolean detectarColisoes(int x, int y, int z) {
        for (int i = 0; i < Obstaculo.numeroDeObstaculos; i++){
            if ((restricoes.get(i)).bloqueiaPassagem(x, y, z)){
                return true;
            }
        }
        return false;
    }

    public void adicionarRobo(Robo robo){
        frota.add(robo);
    }

    public void removerRobo(Robo robo){
        frota.remove(robo);
    }


    public void adicionarObstaculo(Obstaculo obstaculo){
        restricoes.add(obstaculo);
    }

    public void removerObstaculo(Obstaculo obstaculo){
        restricoes.remove(obstaculo);
    }

    public String toString(){
        String out = "";
        out += "Ambiente " + getNome();
        out += "\n Comprimento: " + getComprimento() + "Largura: " + getLargura() + "Altura: " + getAltura();
        out += "\nLista de Robôs presentes no ambiente --------------------------------------------\n";
        for (int i = 0; i < frota.size(); i++){
            out += frota.get(i) + "\n";
        }
        out += "Lista de Obstáculos presentes no ambiente -----------------------------------------\n";
        for (int i = 0; i < restricoes.size(); i++){
            out += restricoes.get(i) + "\n";
        }
        return out;
    }
}
