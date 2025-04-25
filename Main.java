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
        Sensor sensor1 = new SensorProximidade(3);
        robo1.setLimiteNumSensores(10);
        robo1.adicionarSensor(sensor1);
        robo1.sensores.get(0).monitorar();

        // Iniciando menu interativo
        System.out.println("Bem vindo(a) ao simulador de robôs");
        System.out.println("Selecione o ambiente escolhido\n Ambiente 1 -> Digite 1\n Ambiente 2 -> Digite 2");
        
    }
    
}