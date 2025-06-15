package main;

import ambiente.Ambiente;
import robo.RoboExplorador;
import robo.ControleMovimento;
import robo.GerenciadorSensores;
import robo.ModuloComunicacao;
import comunicacao.Logger;
import comunicacao.Comunicador;
import sensores.Sensor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarregadorConfiguracao {
    public static class Configuracao {
        public List<RoboExplorador> robos;
        public Ambiente ambiente;

        public Configuracao(List<RoboExplorador> robos, Ambiente ambiente) {
            this.robos = robos;
            this.ambiente = ambiente;
        }
    }

    public static Configuracao carregar(String arquivoConfig) throws IOException {
        List<RoboExplorador> robos = new ArrayList<>();
        Ambiente ambiente = null;
        int largura = 0;
        int altura = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoConfig))) {
            String linha;
            boolean lendoAmbiente = false;
            List<String> linhasAmbiente = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                if (linha.equals("[AMBIENTE]")) {
                    lendoAmbiente = true;
                    continue;
                }

                if (lendoAmbiente) {
                    if (linha.equals("[ROBOS]")) {
                        lendoAmbiente = false;
                        // Criar ambiente com as dimensões corretas
                        altura = linhasAmbiente.size();
                        largura = linhasAmbiente.get(0).length();
                        ambiente = new Ambiente(largura, altura);
                        // Preencher o ambiente
                        for (int y = 0; y < altura; y++) {
                            String linhaAmb = linhasAmbiente.get(y);
                            for (int x = 0; x < largura; x++) {
                                ambiente.setElemento(x, y, linhaAmb.charAt(x));
                            }
                        }
                        continue;
                    }
                    linhasAmbiente.add(linha);
                }
            }
        }

        if (ambiente == null) {
            throw new IOException("Configuração do ambiente não encontrada");
        }

        // Declarar ambienteFinal aqui, fora do bloco de leitura
        final Ambiente ambienteFinal = ambiente;

        // Agora criar os robôs usando ambienteFinal
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoConfig))) {
            String linha;
            boolean lendoRobos = false;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                if (linha.equals("[ROBOS]")) {
                    lendoRobos = true;
                    continue;
                }

                if (lendoRobos && linha.startsWith("ROBO:")) {
                    String[] partes = linha.substring(5).split(",");
                    String nome = partes[0].trim();
                    int x = Integer.parseInt(partes[1].trim());
                    int y = Integer.parseInt(partes[2].trim());

                    ControleMovimento movimento = new ControleMovimento() {
                        private RoboExplorador roboAtual;

                        @Override
                        public void mover(int x, int y) {
                            if (roboAtual != null) {
                                roboAtual.setPosicao(x, y);
                                System.out.println(roboAtual.getNome() + " movendo para (" + x + "," + y + ")");
                            }
                        }

                        @Override
                        public void setRobo(RoboExplorador robo) {
                            this.roboAtual = robo;
                        }

                        @Override
                        public boolean verificarMovimento(int x, int y) {
                            return ambienteFinal.posicaoValida(x, y) && ambienteFinal.getElemento(x, y) == '.';
                        }
                    };

                    GerenciadorSensores sensores = new GerenciadorSensores() {
                        private List<Sensor> listaSensores = new ArrayList<>();

                        @Override
                        public void adicionarSensor(Sensor sensor) {
                            listaSensores.add(sensor);
                        }

                        @Override
                        public void removerSensor(Sensor sensor) {
                            listaSensores.remove(sensor);
                        }

                        @Override
                        public List<Sensor> getSensores() {
                            return new ArrayList<>(listaSensores);
                        }

                        @Override
                        public void lerSensores() {
                            for (Sensor sensor : listaSensores) {
                                if (sensor.estaOperacional()) {
                                    sensor.lerDados();
                                }
                            }
                        }

                        @Override
                        public boolean verificarCondicoesSeguranca() {
                            for (Sensor sensor : listaSensores) {
                                if (!sensor.estaOperacional()) {
                                    return false;
                                }
                            }
                            return true;
                        }

                        @Override
                        public boolean verificarObstaculos(int x, int y) {
                            return ambienteFinal.getElemento(x, y) == '#';
                        }
                    };

                    ModuloComunicacao comunicacao = new ModuloComunicacao() {
                        private Comunicador comunicador;

                        @Override
                        public void inicializar() {
                            // Implementação vazia
                        }

                        @Override
                        public void enviarMensagem(String mensagem) {
                            if (comunicador != null) {
                                comunicador.enviarMensagem(mensagem);
                            }
                        }

                        @Override
                        public void registrarComunicador(Comunicador comunicador) {
                            this.comunicador = comunicador;
                        }
                    };

                    // Criar o robô com os subsistemas
                    RoboExplorador robo = new RoboExplorador(nome, ambiente,
                                                           movimento, sensores, comunicacao);
                    movimento.setRobo(robo); // Conectar o robô ao controle de movimento
                    // Configurar o logger
                    Logger logger = new Logger(nome);
                    logger.conectar();
                    robo.setComunicador(logger);
                    robos.add(robo);
                }
            }
        }

        return new Configuracao(robos, ambiente);
    }
} 