import java.util.ArrayList;

private class Ambiente
{
    private int largura;
    private int altura;
    public static ArrayList<Robo> hangar;

    //contrutor
    
    private Ambiente(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }

    //metodos

    private boolean dentroDosLimites(int x, int y, int z){
        if( (x >= 0) && (x < largura) && (y >= 0) && (y < altura) && (z < alturaMaxima)){

            return true;            
        }
        else{

            System.out.println("fora dos limites");

            return false;
        }
    }

    private void adicionarRobo(Robo robo){

    }

}

