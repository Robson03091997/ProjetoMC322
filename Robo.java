public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;

    //declarando construtor
    public Robo(String nome, int posicaoX, int posicaoY){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    //declarando métodos para mover o robo e para exibir a posição atual
    public void mover (int deltaX, int deltaY){
        this.posicaoX = posicaoX + deltaX;
        this.posicaoY = posicaoY + deltaY;
    }

    public void exibirPosicao(){
        System.out.println("Posição atual de" + ": " + this.nome + "(" + this.posicaoX + ", " + this.posicaoY + ")");
    }
    
}