import java.util.LinkedList;
import java.util.Queue;

public class ResolvedorLabirintoBFS {
    private Labirinto labirinto;
    private Grafo grafo;

    // Construtor que aceita um objeto Labirinto e inicializa o grafo.
    public ResolvedorLabirintoBFS(Labirinto labirinto) {
        this.labirinto = labirinto;
        this.grafo = new Grafo(labirinto.obterLargura() * labirinto.obterAltura());
        converterLabirintoEmGrafo();
    }

    //Converte o labirinto em um grafo.
    private void converterLabirintoEmGrafo() {
        int[][] matriz = labirinto.obterLabirinto();

        for (int y = 0; y < labirinto.obterAltura(); y++) {
            for (int x = 0; x < labirinto.obterLargura(); x++) {
                if (matriz[y][x] == 0) { // Se é um caminho
                    int verticeAtual = y * labirinto.obterLargura() + x;

                    // Verifica adjacências possíveis e adiciona arestas conforme necessário
                    if (y > 0 && matriz[y-1][x] == 0) {
                        grafo.adicionaAresta(verticeAtual, (y-1) * labirinto.obterLargura() + x);
                    }
                    if (y < labirinto.obterAltura() - 1 && matriz[y+1][x] == 0) {
                        grafo.adicionaAresta(verticeAtual, (y+1) * labirinto.obterLargura() + x);
                    }
                    if (x > 0 && matriz[y][x-1] == 0) {
                        grafo.adicionaAresta(verticeAtual, y * labirinto.obterLargura() + x-1);
                    }
                    if (x < labirinto.obterLargura() - 1 && matriz[y][x+1] == 0) {
                        grafo.adicionaAresta(verticeAtual, y * labirinto.obterLargura() + x+1);
                    }
                }
            }
        }
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
        int inicio = 0;
        int fim = (labirinto.obterAltura() - 1) * labirinto.obterLargura() + labirinto.obterLargura() - 1;
        return BFS(inicio, fim);
    }

    // Para uso em uma apresentação de console
    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }
}