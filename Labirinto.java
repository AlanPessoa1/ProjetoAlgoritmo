import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Labirinto {
    private int[][] matrizLabirinto;
    private int largura;
    private int altura;

    public Labirinto(String nomeArquivo) throws IOException {
        LinkedList<String> linhas = new LinkedList<>();
        String linha; // Declarando 'linha' aqui
    
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
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
            linha = linhas.get(y); // Usando 'linha' aqui
            for (int x = 0; x < largura; x++) {
                matrizLabirinto[y][x] = Character.getNumericValue(linha.charAt(x));
            }
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
}
