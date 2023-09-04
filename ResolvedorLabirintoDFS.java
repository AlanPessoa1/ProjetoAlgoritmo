public class ResolvedorLabirintoDFS extends ResolvedorLabirinto {
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

