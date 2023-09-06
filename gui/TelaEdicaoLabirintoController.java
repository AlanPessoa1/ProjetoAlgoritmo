package gui;

import models.Labirinto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class TelaEdicaoLabirintoController {

    Stage stage;
    Scene scene;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnProximaTela2;

    @FXML
    private GridPane gridLabirinto;


    public void initialize() throws IOException {
        Labirinto labirinto = new Labirinto("mapaLabirinto.txt");

        criarLabirintoVisual(labirinto.obterLabirinto());
    }
    @FXML
    public void criarLabirintoVisual(int[][] matrizLabirinto) {
        for (int y = 0; y < matrizLabirinto.length; y++) {
            for (int x = 0; x < matrizLabirinto[y].length; x++) {
                ImageView imageView = new ImageView();

                if (matrizLabirinto[y][x] == 0) {
                    imageView.setImage(new Image("caminho5.jpg"));
                } else {
                    imageView.setImage(new Image("parede5.jpg"));
                }

                gridLabirinto.add(imageView, x, y);
            }
        }
    }

    @FXML
    void editarLabirinto(ActionEvent event) {

    }

    @FXML
    void irTelaLabirinto(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("Se você mudar de tela, não será possível retornar para a tela de edição.");

        ButtonType novoBotao = new ButtonType("CANCELAR", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().addAll(novoBotao);

        ButtonType botaoPadrao = ButtonType.OK;
        Button botaoPadraoButton = (Button) alert.getDialogPane().lookupButton(botaoPadrao);
        botaoPadraoButton.setText("CONFIRMAR");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == botaoPadrao) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telaJogador.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("TELA JOGO");
            stage.setScene(scene);
            stage.show();
        }
    }


}




