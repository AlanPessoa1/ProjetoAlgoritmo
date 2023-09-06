/*import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        // Verifica se o caminho do arquivo foi fornecido
        if (args.length < 1) {
            System.out.println("Por favor, forneça o caminho para o arquivo do labirinto.");
            return;
        }

        String caminhoArquivo = args[0];

        try {
            // Carrega o labirinto e tenta resolvê-lo
            Labirinto labirinto = new Labirinto(caminhoArquivo);
            ResolvedorLabirinto resolvedor = new ResolvedorLabirinto(labirinto);
            System.out.println(resolvedor.apresentarResultado());
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo do labirinto. Verifique se o caminho está correto e tente novamente.");
            // Considerando que esta é uma aplicação de terminal, podemos querer ver a stack trace para debugging.
            e.printStackTrace();
        }
    }
}*/