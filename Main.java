import java.util.Scanner;

import ambientes.Ambiente;
import ambientes.obstaculos.TipoObstaculo;
import ambientes.robos.Robo;
import ambientes.robos.robosaereos.RoboAereo;
import ambientes.robos.robosaereos.RoboJato;
import ambientes.robos.robosterrestres.RoboColheitadera;
import ambientes.robos.robosterrestres.RoboPreparaSolo;
import ambientes.robos.robosterrestres.RoboTerrestre;
import ambientes.sensores.Sensor;
import ambientes.sensores.SensorProximidade;

import java.util.ArrayList;
public class Main {

    //Métodos para o menu interativo ---------------------------------------------------
    public static void menuAmbiente(ArrayList<Ambiente> ecossistema){
        Scanner input = new Scanner(System.in);
        System.out.println("Bem vindo(a) ao simulador de robôs");
        for (int i = 0; i < ecossistema.size(); i++){
            System.out.printf("%s  (%d)     ", ecossistema.get(i).getNome(), (i+1));
        }
        System.out.println("Selecione o ambiente escolhido  \nFechar o menu -> Digite 0");
        int escolhaInt = -12;
        try {
            escolhaInt = input.nextInt();
            System.out.println(escolhaInt);
            input.nextLine();
        }
        catch (NumberFormatException e) {
            System.err.println("Formato do número errado ou um não número foi usado, tente novamente!");
            menuAmbiente(ecossistema);
        }
        if (escolhaInt > 0 && escolhaInt < ecossistema.size()){
            menuOpcoesAmbiente((escolhaInt-1), ecossistema);
        }
        else if (escolhaInt == 0){
            System.out.println("Obrigado por usar nosso simulador!");;
        }
        else if (escolhaInt < 0 && escolhaInt > ecossistema.size()){
            System.out.println("Valor de ambiente não disponível, tente novamente!");
            menuAmbiente(ecossistema);
        }
        input.close();
    }

    public static void menuOpcoesAmbiente(int opcao, ArrayList<Ambiente> ecossistema){
        Scanner input = new Scanner(System.in);
        System.out.println("Opções: \nInformações do Ambiente -> Digite 1");
        System.out.println("Informações dos robôs -> Digite 2");
        System.out.println("Informações dos obstaculos -> Digite 3");
        System.out.println("Opções a cerca de robôs -> Digite 4");
        //System.out.println("Opções a cerca de obstáculos -> Digite 5\n");
        //System.out.println("Adicionar robos ao ambiente -> Digite 6");
        System.out.println("Remover robos do ambiente -> Digite 5");
        //System.out.println("Adicionar obstáculo ao ambiente -> Digite 8");
        System.out.println("Remover obstáculo do ambiente -> Digite 6");
        System.out.println("Voltar para menu anterior -> Digite -1");
        System.out.println("Fechar o menu -> Digite 0");
        int escolhaOpcao = input.nextInt();
        input.nextLine();
        switch (escolhaOpcao) {
            case 1:
                System.out.println(ecossistema.get(opcao));
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
            case 2:
                for (Robo robo : ecossistema.get(opcao).getFrota()){
                    System.out.println(robo);
                }
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
            case 3:
                for (int i = 0; i < ecossistema.get(opcao).getRestricoes().size(); i++){
                    System.out.printf("Obstaculo %d ", (i+1));
                    System.out.println(ecossistema.get(opcao).getRestricoes().get(i));
                }
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
            case 4:
                menuOpcoesRobos(ecossistema.get(opcao), ecossistema);
                break;
            /*case "5":
                menuOpcoesObstaculos();*/
            /*case"6":
                System.out.println("Digite o ");
                ecossistema.get(opcao).adicionarRobo(null);
                break;*/
            case 5:
                System.out.println("Digite o nome do robo a ser removido");
                for (Robo robo : ecossistema.get(opcao).getFrota()){
                    System.out.println(robo.getNome() + "  ");
                }
                String roboRemover = input.nextLine();
                ecossistema.get(opcao).removerRobo(roboRemover);
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
            //case"8":
                
            case 6:
                System.out.println("Digite o número do obstáculo a ser removido");
                for (int i = 0; i < ecossistema.get(opcao).getRestricoes().size(); i++){
                    System.out.println("("+(i+1)+")"+ecossistema.get(opcao).getRestricoes().get(i));
                }
                int obstaculoRemover = (Integer.parseInt(input.nextLine())-1);
                ecossistema.get(opcao).removerObstaculo(obstaculoRemover);
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
            case -1:
                menuAmbiente(ecossistema);
                break;
            case 0:
                System.out.println("Obrigado por usar nosso simulador!");
                break;
            default:
                System.out.println("Valor não aceito, tente novamente.");
                menuOpcoesAmbiente(opcao, ecossistema);
                break;
        }
        input.close();
    }

    public static void menuOpcoesRobos(Ambiente ambiente, ArrayList<Ambiente> ecossistema){
        Scanner input = new Scanner(System.in);
        System.out.println("Informações dos robôs -> Digite 1");
        System.out.println("Selecionar Robo -> Digite 2");
        System.out.println("Voltar para menu anterior -> Digite -1");
        System.out.println("Fechar o menu -> Digite 0\n");
        int escolhaRobo = input.nextInt();
        input.nextLine();
        switch (escolhaRobo) {
            case 1:
                for (Robo robo : ambiente.getFrota()){
                    System.out.println(robo);
                }
                menuOpcoesRobos(ambiente, ecossistema);
                break;
            case 2:
                System.out.println("Digite o nome do robo a ser selecionado");
                for (Robo robo : ambiente.getFrota()){
                    System.out.println(robo.getNome() + "  ");
                }
                String selecionaRobo = input.nextLine();
                int verifica = 0;
                for (int i = 0; i < ambiente.getFrota().size(); i++){
                    if (ambiente.getFrota().get(i).getNome().equals(selecionaRobo)){
                        menuRobo(ambiente.getFrota().get(i), ambiente, ecossistema);
                        verifica = 1;
                    }
                }
                if (verifica == 0){
                    System.out.println("Robo não encontrado, tente novamente.");
                    menuOpcoesRobos(ambiente, ecossistema);
                }
                break;
            case -1:
                for (int i = 0; i < ecossistema.size(); i++){
                    if (ambiente == ecossistema.get(i)){
                        menuOpcoesAmbiente(i, ecossistema);
                        break;
                    }
                }
                break;
            case 0:
                System.out.println("Obrigado por usar nosso simulador!");
                break;
            default:
                System.out.println("Valor não aceito, tente novamente.");
                menuOpcoesRobos(ambiente, ecossistema);
                break;
        }
        input.close();
    }

    public static void menuRobo(Robo robo, Ambiente ambiente, ArrayList<Ambiente> ecossistema){
        Scanner input = new Scanner(System.in);
        robo.exibirPosicao();
        System.out.println("Para mover o robo -> Digite 1");
        System.out.println("Listar sensores -> Digite 2");
        System.out.println("Ativar/Desativar sensor -> Digite 3");
        System.out.println("Voltar para o menu anterior -> Digite -1");
        System.out.println("Fechar o menu -> Digite 0");
        int usaRobo = input.nextInt();
        input.nextLine();
        switch (usaRobo) {
            case 1:
                System.out.println("Digite para que posição você quer que o robô se mova no formato X Y Z");
                String moveRobo = input.nextLine();
                String[] posicoes = moveRobo.split("\\s+");
                robo.mover(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1]), Integer.parseInt(posicoes[2]));
                menuRobo(robo, ambiente, ecossistema);
                break;
            case 2:
                for (int i = 0; i < robo.sensores.size(); i++){
                    System.out.println(robo.sensores.get(i)+"  ");
                }
                menuRobo(robo, ambiente, ecossistema);
                break;
            case 3:
                for (int i = 0; i < robo.sensores.size(); i++){
                    System.out.println(robo.sensores.get(i)+" ("+(i+1)+")  ");
                }
                System.out.println("Digite o número do sensor correspondente");
                int selecionaSensor = input.nextInt();
                input.nextLine();
                if (selecionaSensor <= robo.getSensores().size()){
                    robo.sensores.get(selecionaSensor-1).monitorar();
                }
                else {
                    System.out.println("Não há o sensor selecionado. Tente novamente.");
                }
                menuRobo(robo, ambiente, ecossistema);
                break;
            case -1:
                menuOpcoesRobos(ambiente, ecossistema);
                break;
            case 0:
                System.out.println("Obrigado por usar nosso simulador!");
                break;
            default:
                System.out.println("Valor não aceito, tente novamente.");
                menuRobo(robo, ambiente, ecossistema);
                break;
        }
        input.close();
    }

    public static void main(String[] args) {
        ArrayList<Ambiente> ecossistema = new ArrayList<Ambiente>();
        Ambiente ambiente = new Ambiente("Ambiente 1", 100, 100, 100);
        ecossistema.add(ambiente);
        Ambiente ambiente1 = new Ambiente("Ambiente 2", 100, 100, 100);
        ecossistema.add(ambiente1);
        RoboTerrestre robo1 = new RoboTerrestre("robo1", 25, 25, "Norte", 100);
        RoboAereo robo2 = new RoboAereo("falcao", 10, 10, "Leste", 80);
        RoboPreparaSolo robo3 = new RoboPreparaSolo("robo3", 60, 30, "Oeste", 40, 1);
        RoboColheitadera robo4 = new RoboColheitadera();
        RoboJato robo5 = new RoboJato("jato", 3, 26, "NORTE", 100, 200);
        ambiente.adicionarObstaculo(10, 33, 0, 10, 10, 10, TipoObstaculo.PREDIO);
        ambiente.adicionarObstaculo(50, 0, 80, 1, 99, 5, TipoObstaculo.FIACAO);
        ambiente.adicionarRobo(robo1);
        ambiente.adicionarRobo(robo2);
        ambiente.adicionarRobo(robo3);
        ambiente.adicionarRobo(robo4);
        ambiente.adicionarRobo(robo5);
        Sensor sensor1 = new SensorProximidade(3);
        robo1.setLimiteNumSensores(10);
        robo1.adicionarSensor(sensor1);

        // Iniciando menu interativo
        Main.menuAmbiente(ecossistema);
    }
}