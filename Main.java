public class Main {
    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100, 100);
        RoboTerrestre robo1 = new RoboTerrestre("robo1", 25, 25, "Norte", 100);
        RoboAereo robo2 = new RoboAereo("falcao", 10, 10, "Leste", 80);
        RoboPreparaSolo robo3 = new RoboPreparaSolo("robo3", 60, 30, "Oeste", 40, 1);
        RoboColheitadera robo4 = new RoboColheitadera();
        ambiente.adicionarRobo(robo1);
        ambiente.adicionarRobo(robo2);
        ambiente.adicionarRobo(robo3);
        ambiente.adicionarRobo(robo4);
        robo1.exibirPosicao();
        robo2.exibirPosicao();
        robo3.exibirPosicao();
        robo4.exibirPosicao();
        robo1.mover(-10, -10, 50);
        robo1.exibirPosicao();
        robo1.mover(15, 15, 110);
        robo2.mover(1,1);
        robo2.exibirPosicao();
        robo2.mover(-7, -7);
        robo3.preparaSolo(100, 100);
        robo4.colhePlantacao(50, 50);
        robo1.exibirPosicao();
        robo2.exibirPosicao();
        robo3.exibirPosicao();
        robo4.exibirPosicao();
    }
    
}