package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
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

    private boolean modoEdicao = false;

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
        gridLabirinto.setHgap(5);
        gridLabirinto.setVgap(5);
        gridLabirinto.setAlignment(Pos.CENTER);

        for (int i = 0; i < matrizLabirinto[0].length; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(10);
            gridLabirinto.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < matrizLabirinto.length; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(30);
            gridLabirinto.getRowConstraints().add(rowConst);
        }

        for (int y = 0; y < matrizLabirinto.length; y++) {
            for (int x = 0; x < matrizLabirinto[y].length; x++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(10);
                imageView.setFitHeight(15);

                if (matrizLabirinto[y][x] == 0) {
                    imageView.setImage(new Image("caminho5.jpg"));

                } else if (matrizLabirinto[y][x] == 1){
                    imageView.setImage(new Image("parede5.jpg"));

                } else if (matrizLabirinto[y][x] == 2) {
                    imageView.setImage(new Image("entrada4.jpg"));

                } else if (matrizLabirinto[y][x] == 3) {
                    imageView.setImage(new Image("saida4.jpg"));

                }

                gridLabirinto.add(imageView, x, y);

                int finalY = y;
                int finalX = x;
                imageView.setOnMouseClicked(e -> {
                    if (!modoEdicao) return;

                    int currentValue = matrizLabirinto[finalY][finalX];
                    switch (currentValue) {
                        case 0:
                            imageView.setImage(new Image("parede5.jpg"));
                            matrizLabirinto[finalY][finalX] = 1;
                            break;
                        case 1:
                            imageView.setImage(new Image("caminho5.jpg"));
                            matrizLabirinto[finalY][finalX] = 0;
                            break;
                        case 2, 3:
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Aviso");
                            alert.setHeaderText(null);
                            alert.setContentText("A entrada e saída do labirinto não podem ser alteradas.");
                            alert.showAndWait();
                            break;
                    }
                });
            }
        }
    }

    @FXML
    void editarLabirinto(ActionEvent event) {
        modoEdicao = !modoEdicao;
        if (modoEdicao) {
            btnEditar.setText("Parar Edição");
        } else {
            btnEditar.setText("Editar Labirinto");
        }
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




