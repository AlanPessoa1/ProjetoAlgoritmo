package models;

import java.util.LinkedList;

public class ResolvedorLabirintoDFS extends ResolvedorLabirinto {

    private LinkedList<Integer> caminho = new LinkedList<>();

    public ResolvedorLabirintoDFS(Labirinto labirinto) {
        super(labirinto);
    }

    // Algoritmo de busca em profundidade (DFS) para encontrar um caminho.
    public boolean DFS(int inicio, int fim) {
        boolean[] visitados = new boolean[labirinto.obterLargura() * labirinto.obterAltura()];
        return DFSUtil(inicio, fim, visitados);
    }

    // Método recursivo utilizado pelo DFS.
    private boolean DFSUtil(int vertice, int fim, boolean[] visitados) {
        if (vertice == fim) {
            caminho.addFirst(vertice);
            return true;
        }

        visitados[vertice] = true;

        for (int adj : grafo.getAdjVertices(vertice)) {
            if (!visitados[adj]) {
                if (DFSUtil(adj, fim, visitados)) {
                    caminho.addFirst(vertice);
                    return true;
                }
            }
        }

        return false;
    }

    public LinkedList<Integer> getCaminhoParaSaida() {
        if (temCaminhoParaSaida()) {
            return caminho;
        }
        return null;
    }

    // Encontra a saída do labirinto.
    public boolean temCaminhoParaSaida() {
        int inicio = labirinto.obterEntrada();
        int fim = labirinto.obterSaida();
        return DFS(inicio, fim);
    }

    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }
}
