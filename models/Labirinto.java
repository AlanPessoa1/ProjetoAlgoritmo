package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Labirinto {
    private int[][] matrizLabirinto;
    private int largura;
    private int altura;
    private Grafo grafo;
    private int entrada; // índice único para a entrada
    private int saida;   // índice único para a saída

    public Labirinto(String mapaLabirinto) throws IOException {
        LinkedList<String[]> linhas = new LinkedList<>();
        String linha;

        try (BufferedReader leitor = new BufferedReader(new FileReader(mapaLabirinto))) {
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha.trim().split(" "));
            }
        }

        altura = linhas.size();
        largura = linhas.get(0).length;

        matrizLabirinto = new int[altura][largura];

        for (int y = 0; y < altura; y++) {
            String[] tokens = linhas.get(y);
            for (int x = 0; x < largura; x++) {
                int currentVal = Integer.parseInt(tokens[x]);
                if (currentVal == 2) {
                    entrada = toSingleIndex(y, x);
                } else if (currentVal == 3) {
                    saida = toSingleIndex(y, x);
                }
                matrizLabirinto[y][x] = currentVal;
            }
        }

        construirGrafo();
    }

    public Labirinto(int[][] matrizFornecida) {
        if (matrizFornecida == null || matrizFornecida.length == 0 || matrizFornecida[0].length == 0) {
            throw new IllegalArgumentException("Matriz fornecida é inválida.");
        }

        this.altura = matrizFornecida.length;
        this.largura = matrizFornecida[0].length;

        this.matrizLabirinto = new int[altura][largura];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                this.matrizLabirinto[y][x] = matrizFornecida[y][x];
                if (matrizFornecida[y][x] == 2) {
                    entrada = toSingleIndex(y, x);
                } else if (matrizFornecida[y][x] == 3) {
                    saida = toSingleIndex(y, x);
                }
            }
        }
        construirGrafo();
    }

    private void construirGrafo() {
        grafo = new Grafo(largura * altura);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (matrizLabirinto[y][x] == 0 || matrizLabirinto[y][x] == 2 || matrizLabirinto[y][x] == 3) {
                    int v = toSingleIndex(y, x);

                    if (y > 0 && (matrizLabirinto[y - 1][x] == 0 || matrizLabirinto[y - 1][x] == 2 ||
                            matrizLabirinto[y - 1][x] == 3)) {
                        grafo.adicionaAresta(v, toSingleIndex(y - 1, x));
                    }

                    if (y < altura - 1 && (matrizLabirinto[y + 1][x] == 0 || matrizLabirinto[y + 1][x] == 2 ||
                            matrizLabirinto[y + 1][x] == 3)) {
                        grafo.adicionaAresta(v, toSingleIndex(y + 1, x));
                    }

                    if (x > 0 && (matrizLabirinto[y][x - 1] == 0 || matrizLabirinto[y][x - 1] == 2 ||
                            matrizLabirinto[y][x - 1] == 3)) {
                        grafo.adicionaAresta(v, toSingleIndex(y, x - 1));
                    }

                    if (x < largura - 1 && (matrizLabirinto[y][x + 1] == 0 || matrizLabirinto[y][x + 1] == 2 ||
                            matrizLabirinto[y][x + 1] == 3)) {
                        grafo.adicionaAresta(v, toSingleIndex(y, x + 1));
                    }
                }
            }
        }
    }

    private int toSingleIndex(int y, int x) {
        return y * largura + x;
    }

    public List<Integer> vizinhosConectados(int indice) {
        int y = indice / largura;
        int x = indice % largura;

        List<Integer> vizinhos = new ArrayList<>();

        // Vizinho acima
        if (y > 0 && matrizLabirinto[y-1][x] == 0) {
            vizinhos.add(toSingleIndex(y-1, x));
        }

        // Vizinho abaixo
        if (y < altura - 1 && matrizLabirinto[y+1][x] == 0) {
            vizinhos.add(toSingleIndex(y+1, x));
        }

        // Vizinho à esquerda
        if (x > 0 && matrizLabirinto[y][x-1] == 0) {
            vizinhos.add(toSingleIndex(y, x-1));
        }

        // Vizinho à direita
        if (x < largura - 1 && matrizLabirinto[y][x+1] == 0) {
            vizinhos.add(toSingleIndex(y, x+1));
        }

        return vizinhos;
    }

    public int[][] obterLabirinto() {
        int[][] copia = new int[altura][];
        for (int i = 0; i < altura; i++) {
            copia[i] = matrizLabirinto[i].clone();
        }
        return copia;
    }

    public int obterLargura() {
        return largura;
    }

    public int obterAltura() {
        return altura;
    }

    public int obterEntrada() {
        return entrada;
    }

    public int obterSaida() {
        return saida;
    }
}