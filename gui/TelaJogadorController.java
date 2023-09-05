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
    private Button btnMoverBaixo;

    @FXML
    private Button btnMoverCima;

    @FXML
    private Button btnMoverDireita;

    @FXML
    private Button btnMoverEsquerda;

    @FXML
    private Button btnResolucao;

    @FXML
    void moverPraBaixo(ActionEvent event) {

    }

    @FXML
    void moverPraCima(ActionEvent event) {

    }

    @FXML
    void moverPraDireita(ActionEvent event) {

    }

    @FXML
    void moverPraEsquerda(ActionEvent event) {

    }

    @FXML
    void irTelaResolucao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telaResolucao.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("TELA RESOLUÇÃO");
        stage.setScene(scene);
        stage.show();
    }

}
