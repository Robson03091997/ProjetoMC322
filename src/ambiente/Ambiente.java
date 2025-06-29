package ambiente;

import robo.RoboExplorador;
import java.util.List;

public class Ambiente {
    private char[][] matriz;
    private int largura;
    private int altura;
    private List<RoboExplorador> robos;

    public Ambiente(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.matriz = new char[altura][largura];
        inicializar();
    }

    private void inicializar() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                matriz[i][j] = '.';
            }
        }
    }

    public boolean posicaoValida(int x, int y) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    public char getElemento(int x, int y) {
        if (posicaoValida(x, y)) {
            return matriz[y][x];
        }
        return '#';
    }

    public void setElemento(int x, int y, char elemento) {
        if (posicaoValida(x, y)) {
            matriz[y][x] = elemento;
        }
    }

    public void adicionarObstaculo(int x, int y) {
        if (posicaoValida(x, y)) {
            matriz[y][x] = '#';
        }
    }

    public void setRobos(List<RoboExplorador> robos) {
        this.robos = robos;
    }

    public void exibir() {
        System.out.println("\nEstado atual do ambiente:");
        // Cria uma cópia da matriz para exibição
        char[][] matrizExibicao = new char[altura][largura];
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                matrizExibicao[i][j] = matriz[i][j];
            }
        }

        // Adiciona os robôs na matriz de exibição
        if (robos != null) {
            for (RoboExplorador robo : robos) {
                int x = robo.getPosicaoX();
                int y = robo.getPosicaoY();
                if (posicaoValida(x, y)) {
                    // Usa a primeira letra do nome do robô para representá-lo
                    matrizExibicao[y][x] = robo.getNome().charAt(0);
                }
            }
        }

        // Exibe a matriz com os robôs
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                System.out.print(matrizExibicao[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nLegenda:");
        System.out.println(". = espaço vazio");
        System.out.println("# = obstáculo");
        System.out.println("* = alvo");
        if (robos != null) {
            for (RoboExplorador robo : robos) {
                System.out.println(robo.getNome().charAt(0) + " = " + robo.getNome());
            }
        }
        System.out.println();
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public void limpar() {
        inicializar();
    }
} 