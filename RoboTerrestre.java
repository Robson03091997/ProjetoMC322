public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;

    protected RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima){
        super(nome, posicaoX, posicaoY, direcao);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    protected void mover (int deltaX, int deltaY, int velocidade){
        if (velocidadeMaxima >= velocidade) {
            this.posicaoX = posicaoX + deltaX;
            this.posicaoY = posicaoY + deltaY;
        }
        else {
            System.out.println("Não é permitido se mover acima da velocidade máxima, movimento negado!");
        }
    }


}
