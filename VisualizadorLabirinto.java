import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VisualizadorLabirinto extends Application {
    
    private Labirinto labirinto;
    private Pane painel = new Pane();

    // Constantes
    private static final int CELL_SIZE = 20;
    private static final Color WALL_COLOR = Color.BLACK;
    private static final Color PATH_COLOR = Color.WHITE;

    @Override
    public void start(Stage primaryStage) {
        try {
    labirinto = new Labirinto("mapaLabirinto.txt");
} catch(IOException e) {
    // Tratar erro de leitura aqui
    System.err.println("Erro ao carregar o labirinto.");
    e.printStackTrace();
    Platform.exit();  // Encerra a aplicação
    return;
}
// Isso garante que, quando o evento de clique é acionado, finalX e finalY têm os valores corretos correspondentes à célula em que o usuário clicou, independentemente da iteração atual dos loops for.
for (int y = 0; y < labirinto.obterAltura(); y++) {
    for (int x = 0; x < labirinto.obterLargura(); x++) {
        final int finalX = x;
        final int finalY = y;
        
        Rectangle rect = new Rectangle(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                
        if (labirinto.obterLabirinto()[y][x] == 1) {
            rect.setFill(WALL_COLOR);
        } else {
            rect.setFill(PATH_COLOR);
        }
                
        rect.setOnMouseClicked(event -> {
            if (rect.getFill().equals(WALL_COLOR)) {
                rect.setFill(PATH_COLOR);
            } else {
                rect.setFill(WALL_COLOR);
            }
            labirinto.alternarCelula(finalX, finalY);  // Use finalX e finalY aqui
        });

        painel.getChildren().add(rect);
    }
}
        
        Scene cena = new Scene(painel, labirinto.obterLargura() * CELL_SIZE, labirinto.obterAltura() * CELL_SIZE);
        primaryStage.setScene(cena);
        primaryStage.setTitle("Visualizador de Labirinto");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}