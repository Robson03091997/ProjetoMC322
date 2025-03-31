import java.util.ArrayList;

public class Ambiente
{
    private int largura;
    private int altura;
    private int alturaMaxima;
    public static ArrayList<Robo> hangar = new ArrayList<>();

    //construtor
    
    public Ambiente(int largura, int altura, int alturaMaxima){
        this.largura = largura;
        this.altura = altura;
    }

    //metodos

    public boolean dentroDosLimites(int x, int y, int z){
        if( (x >= 0) && (x < largura) && (y >= 0) && (y < altura) && (z < alturaMaxima)){

            return true;            
        }
        else{

            System.out.println("fora dos limites");

            return false;
        }
    }

    public void adicionarRobo(Robo robo){
        hangar.add(robo);
    }

}