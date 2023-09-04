public abstract class ResolvedorLabirinto {
    protected Labirinto labirinto;
    protected Grafo grafo;

    public ResolvedorLabirinto(Labirinto labirinto) {
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

    public abstract boolean temCaminhoParaSaida();

    public String apresentarResultado() {
        return temCaminhoParaSaida() ? "Caminho encontrado!" : "Não há saída!";
    }
}