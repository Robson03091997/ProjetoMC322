public class RoboPreparaSolo extends RoboTerreste {
    //robo para agricultura que prepara o solo usando arados, grades e subsoladores
    private int distanciaCorredores; //em metros
    private boolean arado = false;
    private boolean grades = false;
    private boolean subsolador = false;

    protected RoboPreparaSolo(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, int distanciaCorredores){
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima);
        this.distanciaCorredores = distanciaCorredores;
    }

    @overload
    protected RoboPreparaSolo(){
        super();
        this.distanciaCorredores = 1;
    }

    protected int getDistanciaCorredores(){
        return this.distanciaCorredores;
    }

    protected void interruptorArado() {
        if (this.getArado()){
            this.arado = false;
        }
        else {
            this.arado = true;
        }
    }

    protected void interruptorGrades() {
        if (this.getGrades()){
            this.grades = false;
        }
        else {
            this.grades = true;
        }
    }

    protected void interruptorSubsolador () {
        if (this.getSubsolador()){
            this.subsolador = false;
        }
        else {
            this.subsolador = true;
        }
    }

    protected boolean getArado() {
        return this.arado;
    }

    protected boolean getGrades() {
        return this.grades;
    }

    protected boolean getSubsolador() {
        return this.subsolador;
    }

    protected void preparaSolo(int largura, int altura){
        if (getArado()){
            interruptorArado();
        }
        if (getGrades()){
            interruptorGrades();
        }
        if (getSubsolador()){
            interruptorSubsolador();
        }
        this.mover(-this.getPosX(), -this.getPosY());
        interruptorArado();
        interruptorGrades();
        interruptorSubsolador();
        while ((this.getPosX() != largura-1) || (this.getPosY() != altura-1)){
            if (this.getPosX() != largura-1) {
                this.mover(largura-1, 0);
            }
            else {
                this.mover(0, 0);
            }
            if (Ambiente.dentroDosLimites(this.getPosX(), this.getPosY() + this.getDistanciaCorredores(), 0)){
                this.mover(0, this.getDistanciaCorredores());
            }
            else break;
        }
        interruptorArado();
        interruptorGrades();
        interruptorSubsolador();
    }

    @overload
    protected void preparaSolo(int posIniX, int posIniY, int posFimX, int posFimY, int velocidade, boolean arado, boolean grades, boolean subsolador){
        if (arado && !getArado()){
            interruptorArado();
        }
        if (grades && !getGrades()){
            interruptorGrades();
        }
        if (subsolador && !getSubsolador()){
            interruptorSubsolador();
        }
        this.mover((posIniX - this.getPosX()), (posIniY - this.getPosY()), velocidade);
        while ((this.getPosX() != posFimX) || (this.getPosY() != posFimY)){
            if (this.getPosX() != posFimX) {
                this.mover((posFimX-posIniX), 0, velocidade);
            }
            else {
                this.mover(posIniX, 0, velocidade);
            }
            if (Ambiente.dentroDosLimites(this.getPosX(), this.getPosY() + this.getDistanciaCorredores(), 0)){
                this.mover(0, this.getDistanciaCorredores(), velocidade);
            }
            else break;
        }
        interruptorArado();
        interruptorGrades();
        interruptorSubsolador();
    }
}
