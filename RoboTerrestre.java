public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;

    //Declarando construtor do RoboTerrestre
    protected RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima){
        super(nome, posicaoX, posicaoY, direcao);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    //Declarando um método construtor com overload para o caso default/sem conhecimento de uso
    protected RoboTerrestre(){
        super();
        this.velocidadeMaxima = 20;
    }

    //Declarando método get para a velocidade máxima

    protected int getVelocidadeMaxima(){
        return velocidadeMaxima;
    }

    //Declarando os métodos de mover do RoboTerrestre, tomando cuidado para não permitir o uso do método da classe mãe para que não se mova sem velocidade definida
    //fazendo override para usar como default a metade da velocidade máxima
    protected void mover (int deltaX, int deltaY){
        this.posicaoX = posicaoX + deltaX;
        this.posicaoY = posicaoY + deltaY;
        System.out.printf("Se movendo para (%d, %d) com velocidade %.2f\n", posicaoX, posicaoY, ((float) velocidadeMaxima/2));
    }


    protected void mover (int deltaX, int deltaY, int velocidade){
        if (velocidadeMaxima >= velocidade) {
            this.posicaoX = this.posicaoX + deltaX;
            this.posicaoY = this.posicaoY + deltaY;
        }
        else {
            System.out.println("Não é permitido se mover acima da velocidade máxima, movimento negado!");
        }
    }


}
