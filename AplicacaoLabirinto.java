import javafx.application.Application;
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
        labirinto = new Labirinto(20, 20);  // Criando um labirinto de 20x20 por exemplo

        for (int y = 0; y < labirinto.obterAltura(); y++) {
            for (int x = 0; x < labirinto.obterLargura(); x++) {
                Rectangle rect = new Rectangle(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                
                if (labirinto.obterLabirinto()[y][x] == 1) {
                    rect.setFill(WALL_COLOR);
                } else {
                    rect.setFill(PATH_COLOR);
                }
                
                rect.setOnMouseClicked(event -> {
                    if (rect.getFill().equals(WALL_COLOR)) {
                        rect.setFill(PATH_COLOR);
                        labirinto.obterLabirinto()[y][x] = 0;
                    } else {
                        rect.setFill(WALL_COLOR);
                        labirinto.obterLabirinto()[y][x] = 1;
                    }
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