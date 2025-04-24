import java.util.ArrayList;

public class Ambiente
{
    private final int largura;
    private final int comprimento;
    private int altura;
    public static ArrayList<Robo> hangar = new ArrayList<>();
    public static ArrayList<Obstaculo> obstaculos = new ArrayList<>();

    //construtor
    
    public Ambiente(int largura, int comprimento, int altura){
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    //metodos

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
            if ((obstaculos.get(i)).bloqueiaPassagem(x, y, z)){
                return true;
            }
        }
        return false;
    }

    public void adicionarRobo(Robo robo){
        hangar.add(robo);
    }

    public void removerRobo(Robo robo){
        hangar.remove(robo);
    }

}