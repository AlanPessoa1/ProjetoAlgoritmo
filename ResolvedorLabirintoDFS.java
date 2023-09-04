public class ResolvedorLabirintoDFS {
    private Labirinto labirinto;
    private Grafo grafo;


    // Construtor que aceita um objeto Labirinto e inicializa o grafo.
    public ResolvedorLabirintoDFS(Labirinto labirinto) {
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

    
    // Algoritmo de busca em profundidade (DFS) para encontrar um caminho.
    public boolean DFS(int inicio, int fim) {
        boolean[] visitados = new boolean[labirinto.obterLargura() * labirinto.obterAltura()];
        return DFSUtil(inicio, fim, visitados);
    }

    
    // Método recursivo utilizado pelo DFS.
    private boolean DFSUtil(int vertice, int fim, boolean[] visitados) {
        if (vertice == fim) return true;
    
        visitados[vertice] = true;
    
        for (int adj : grafo.getAdjVertices(vertice)) {  // Note que mudamos aqui
            if (!visitados[adj]) {
                if (DFSUtil(adj, fim, visitados)) return true;
            }
        }
    
        return false;
    }

    // Encontra a saída do labirinto.
    public boolean temCaminhoParaSaida() {
        int inicio = 0;
        int fim = (labirinto.obterAltura() - 1) * labirinto.obterLargura() + labirinto.obterLargura() - 1;
        return DFS(inicio, fim);
    }

    
    // Para uso em uma apresentação de console
    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }
}

