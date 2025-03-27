public class Robo {
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;
    public static int numeroDeRobos = 0;

    //declarando construtores ------------------------------------------------------
    protected Robo(String nome, int posicaoX, int posicaoY, String direcao){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        Robo.numeroDeRobos++;
    }

    @overload
    protected Robo(){
        this.nome = "Robo"+toString(Robo.numeroDeRobos);
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.direcao = "Não definida";
        Robo.numeroDeRobos++;
    }

    //Declarando métodos get ----------------------------------------------------
    protected String getNome(){
        return this.nome;
    }

    protected int getPosX() {
        return this.posicaoX;
    }

    protected int getPosY() {
        return this.posicaoY;
    }

    protected String getDirecao() {
        return this.direcao;
    }

    //Declarando métodos set --------------------------------------------------------------------------------
    protected void setNome(String nome){
        this.nome = nome;
    }

    protected void setDirecao(String direcao){
        this.direcao = direcao;
    }


    //Declarando métodos para mover o robo e para exibir a posição atual -------------------------------------------------------------
    protected void mover (int deltaX, int deltaY){
        this.posicaoX = this.posicaoX + deltaX;
        this.posicaoY = this.posicaoY + deltaY;
    }

    protected void exibirPosicao(){
        System.out.println("Posição atual de" + ": " + this.nome + "(" + this.posicaoX + ", " + this.posicaoY + ")");
    }

    //Declarando método de identificação de obstáculos
    protected void identificarObstaculo(){
        Ambiente.hangar.forEach(robo -> System.out.println("Robo: " + robo.getNome() + " posição (" + robo.getPosX() + ", " + robo.getPosY()+")"));
    }
    
}