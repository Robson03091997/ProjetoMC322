package main;

import ambiente.Ambiente;
import robo.RoboExplorador;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Carrega a configuração do simulador
            var config = CarregadorConfiguracao.carregar("config/simulador.txt");

            // Cria e exibe o menu de missões
            MenuMissao menu = new MenuMissao(config.robos, config.ambiente);
            menu.exibirMenu();

        } catch (Exception e) {
            System.err.println("Erro ao executar o simulador: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 