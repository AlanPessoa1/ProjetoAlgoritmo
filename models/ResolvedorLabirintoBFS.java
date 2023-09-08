package models;

import java.util.LinkedList;
import java.util.Queue;

public class ResolvedorLabirintoBFS extends ResolvedorLabirinto {

    private LinkedList<Integer> caminho = new LinkedList<>();

    public ResolvedorLabirintoBFS(Labirinto labirinto) {
        super(labirinto);
    }

    // Algoritmo de busca em largura (BFS) para encontrar um caminho.
    public boolean BFS(int inicio, int fim) {
        boolean[] visitados = new boolean[labirinto.obterLargura() * labirinto.obterAltura()];
        int[] pais = new int[labirinto.obterLargura() * labirinto.obterAltura()]; // Para rastrear os pais dos vértices
        Queue<Integer> fila = new LinkedList<>();

        visitados[inicio] = true;
        fila.add(inicio);

        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            if (vertice == fim) {
                reconstituirCaminho(pais, inicio, fim);
                return true;
            }

            for (int adj : grafo.getAdjVertices(vertice)) {
                if (!visitados[adj]) {
                    visitados[adj] = true;
                    pais[adj] = vertice; // O vértice atual é o pai do vértice adjacente
                    fila.add(adj);
                }
            }
        }
        return false;
    }

    // Encontra a saída do labirinto usando BFS.
    public boolean temCaminhoParaSaida() {
        int inicio = labirinto.obterEntrada();
        int fim = labirinto.obterSaida();
        return BFS(inicio, fim);
    }

    // Reconstitui o caminho a partir dos pais e o armazena em 'caminho'
    private void reconstituirCaminho(int[] pais, int inicio, int fim) {
        int atual = fim;
        while (atual != inicio) {
            caminho.addFirst(atual);
            atual = pais[atual];
        }
        caminho.addFirst(inicio);
    }

    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }

    public LinkedList<Integer> obterCaminho() {
        return caminho;
    }
}
