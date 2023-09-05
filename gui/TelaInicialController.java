package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaInicialController {

    Stage stage;
    Scene scene;

    @FXML
    private Button btnJogar;

    @FXML
    private Button btnResolucao;

    @FXML
    private AnchorPane painelLabirinto;

    @FXML
    private TextArea txtLabirinto;


    @FXML
    void irTelaLabirinto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telaJogador.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("TELA JOGADOR");
        stage.setScene(scene);
        stage.show();
    }

}
