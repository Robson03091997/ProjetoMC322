public class Ambiente
{
    public int largura;
    public int altura;

    //contrutor
    
    public Ambiente(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }

    //metodos

    public boolean dentroDosLimites(int x, int y){
        if( (x >= 0) && (x < largura) && (y >= 0) && (y < altura)){

            return true;            
        }
        else{

            System.out.println("fora dos limites");

            return false;
        }

    }

}

