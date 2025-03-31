public class RoboColheitadera extends RoboTerrestre {
    private boolean foice;

    public RoboColheitadera(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, boolean foice){
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima);
        this.foice = foice;
    }

    @overload
    public RoboColheitadera(){
        super();
        this.foice = false;
    }

    private boolean getFoice(){
        return this.foice;
    }

    private void interruptorFoice(){
        if (this.getFoice()){
            this.foice = false;
        }
        else {
            this.foice = true;
        }
    }

    protected void colhePlantacao(int largura, int altura){
        if (foice){
            interruptorFoice();
        }
        this.mover(-this.getPosX(), -this.getPosY());

        interruptorFoice();

        while ((this.getPosX() != largura-1) || (this.getPosY() != altura-1)){
            if (this.getPosX() != largura-1) {
                this.mover(largura-1, 0);
            }
            else {
                this.mover(0, 0);
            }
            if (Ambiente.dentroDosLimites(this.getPosX(), this.getPosY() + 1, 0)){
                this.mover(0, 1);
            }
            else break;
        }
        interruptorFoice();
    }

    @overload
    protected void preparaSolo(int posIniX, int posIniY, int posFimX, int posFimY, int velocidade, boolean arado, boolean grades, boolean subsolador){
        if (foice){
            interruptorFoice();
        }
        this.mover((posIniX - this.getPosX()), (posIniY - this.getPosY()), velocidade);
        interruptorFoice();
        while ((this.getPosX() != posFimX) || (this.getPosY() != posFimY)){
            if (this.getPosX() != posFimX) {
                this.mover((posFimX-posIniX), 0, velocidade);
            }
            else {
                this.mover(posIniX, 0, velocidade);
            }
            if (Ambiente.dentroDosLimites(this.getPosX(), this.getPosY() + 1, 0)){
                this.mover(0, 1, velocidade);
            }
            else break;
        }
        interruptorFoice();
    }
    
}
