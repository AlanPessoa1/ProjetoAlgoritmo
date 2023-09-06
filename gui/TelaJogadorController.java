package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaJogadorController {

    Stage stage;
    Scene scene;

    @FXML
    private Button btnResolucao;

    @FXML
    private Button btnResolucaoOriginal;

    @FXML
    void mostrarResolucaoLabirinto(ActionEvent event) {

    }

    @FXML
    void irTelaResolucaoOriginal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telaResolucaoOriginal.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("RESOLUÇÃO ORIGINAL");
        stage.setScene(scene);
        stage.show();
    }

}