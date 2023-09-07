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
        LinkedList<String> linhas = new LinkedList<>();
        String linha;

        try (BufferedReader leitor = new BufferedReader(new FileReader(mapaLabirinto))) {
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        }

        altura = linhas.size();
        largura = linhas.get(0).length();

        for (String l : linhas) {
            if (l.length() != largura) {
                throw new IOException("O arquivo do labirinto está mal-formatado. Nem todas as linhas têm o mesmo comprimento.");
            }
        }

        matrizLabirinto = new int[altura][largura];

        for (int y = 0; y < altura; y++) {
            linha = linhas.get(y);
            for (int x = 0; x < largura; x++) {
                char currentChar = linha.charAt(x);
                if (currentChar == 2) {
                    entrada = toSingleIndex(y, x);
                    matrizLabirinto[y][x] = 2;
                } else if (currentChar == 3) {
                    saida = toSingleIndex(y, x);
                    matrizLabirinto[y][x] = 3;
                } else {
                    matrizLabirinto[y][x] = Character.getNumericValue(currentChar);
                }
            }
        }

        construirGrafo();
    }

    private void construirGrafo() {
        grafo = new Grafo(largura * altura);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (matrizLabirinto[y][x] == 0) {
                    int v = toSingleIndex(y, x);

                    if (y > 0 && matrizLabirinto[y - 1][x] == 0) grafo.adicionaAresta(v, toSingleIndex(y - 1, x));
                    if (y < altura - 1 && matrizLabirinto[y + 1][x] == 0) grafo.adicionaAresta(v, toSingleIndex(y + 1, x));
                    if (x > 0 && matrizLabirinto[y][x - 1] == 0) grafo.adicionaAresta(v, toSingleIndex(y, x - 1));
                    if (x < largura - 1 && matrizLabirinto[y][x + 1] == 0) grafo.adicionaAresta(v, toSingleIndex(y, x + 1));
                }
            }
        }
    }

    private int toSingleIndex(int y, int x) {
        return y * largura + x;
    }

    public List<Integer> vizinhosConectados(int indice) {
        int x = indice / largura;
        int y = indice % largura;

        List<Integer> vizinhos = new ArrayList<>();

        // Vizinho acima
        if (x > 0 && matrizLabirinto[x-1][y] == 0) {
            vizinhos.add(toSingleIndex(x-1, y));
        }

        // Vizinho abaixo
        if (x < altura - 1 && matrizLabirinto[x+1][y] == 0) {
            vizinhos.add(toSingleIndex(x+1, y));
        }

        // Vizinho à esquerda
        if (y > 0 && matrizLabirinto[x][y-1] == 0) {
            vizinhos.add(toSingleIndex(x, y-1));
        }

        // Vizinho à direita
        if (y < largura - 1 && matrizLabirinto[x][y+1] == 0) {
            vizinhos.add(toSingleIndex(x, y+1));
        }

        return vizinhos;
    }


    public void alternarCelula(int x, int y) {
        if (matrizLabirinto[y][x] == 0) {
            matrizLabirinto[y][x] = 1;
        } else {
            matrizLabirinto[y][x] = 0;
        }
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
