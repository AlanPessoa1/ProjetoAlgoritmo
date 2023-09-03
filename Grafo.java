import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<Integer, List<Integer>> mapa = new HashMap<>();

    
    public Grafo(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            mapa.put(i, new LinkedList<>());
        }
    }
    // Método original para adicionar um nó baseado em string
    public void adicionaNo(int no) {
        mapa.putIfAbsent(no, new LinkedList<>());
    }
    // Método original para adicionar uma aresta entre dois nós baseados em strings
    public void adicionaAresta(int no1, int no2) {
        mapa.get(no1).add(no2);
        mapa.get(no2).add(no1);
    }
    // Método original para remover uma aresta entre dois nós
    public void removeAresta(int no1, int no2) {
        List<Integer> eV1 = mapa.get(no1);
        List<Integer> eV2 = mapa.get(no2);
        if (eV1 != null)
            eV1.remove((Integer) no2);  // Cast é necessário aqui para remover o objeto e não o índice
        if (eV2 != null)
            eV2.remove((Integer) no1);  // Mesmo motivo acima
    }
    // Método para obter vértices adjacentes com base em um identificador inteiro
    public List<Integer> getAdjVertices(int id) {
        return mapa.get(id);
    }
}