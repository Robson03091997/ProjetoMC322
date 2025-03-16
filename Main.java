public class Main {
    public static void main(String[] args) {
        Robo robo0 = new Robo("naruto", 3, 6);
        Ambiente ambiente = new Ambiente(10, 10);
        robo0.exibirPosicao();
        if (ambiente.dentroDosLimites(0, 0)){
            robo0.mover(-3, -6);
        }
        robo0.exibirPosicao();
        if (ambiente.dentroDosLimites(10, 1)){
            robo0.mover(10, 1);
        }
        robo0.exibirPosicao();
        if (ambiente.dentroDosLimites(5, 5)){
            robo0.mover(5, 5);
        }
        robo0.exibirPosicao();

    }
    
}
