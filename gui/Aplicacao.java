package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Aplicacao extends Application {

    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(Aplicacao.class.getResource("telaInicial.fxml")));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("LABIRINTO ORIGINAL");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

