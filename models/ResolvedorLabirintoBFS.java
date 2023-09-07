package models;

import java.util.LinkedList;
import java.util.Queue;

public class ResolvedorLabirintoBFS extends ResolvedorLabirinto {
    public ResolvedorLabirintoBFS(Labirinto labirinto) {
        super(labirinto);
    }

    // Algoritmo de busca em largura (BFS) para encontrar um caminho.
    public boolean BFS(int inicio, int fim) {
        boolean[] visitados = new boolean[labirinto.obterLargura() * labirinto.obterAltura()];
        Queue<Integer> fila = new LinkedList<>();

        visitados[inicio] = true;
        fila.add(inicio);

        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            if (vertice == fim) return true;

            for (int adj : grafo.getAdjVertices(vertice)) {
                if (!visitados[adj]) {
                    visitados[adj] = true;
                    fila.add(adj);
                }
            }
        }
        return false;
    }

    // Encontra a saída do labirinto usando BFS.
    public boolean temCaminhoParaSaida() {
        int inicio = 2;
        int fim = (labirinto.obterAltura() - 1) * labirinto.obterLargura() + labirinto.obterLargura() - 1;
        return BFS(inicio, fim);
    }

    // Para uso em uma apresentação de console
    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }
}
