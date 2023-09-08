package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import models.Labirinto;
import models.ResolvedorLabirintoBFS;
import models.ResolvedorLabirintoDFS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TelaJogadorController {

    Stage stage;
    Scene scene;

    @FXML
    private Button btnResolucao;

    @FXML
    private Button btnResolucaoOriginal;

    @FXML
    private GridPane gridLabirintoEditado;

    public void initialize() {
        Labirinto labirinto;
        labirinto = new Labirinto(TelaEdicaoLabirintoController.getInstancia().getMatrizLabirintoEditado());
        criarLabirintoVisual2(labirinto.obterLabirinto());
    }

    @FXML
    public void criarLabirintoVisual2(int[][] matrizLabirinto) {
        gridLabirintoEditado.setHgap(5);
        gridLabirintoEditado.setVgap(5);
        gridLabirintoEditado.setAlignment(Pos.CENTER);

        for (int i = 0; i < matrizLabirinto[0].length; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(10);
            gridLabirintoEditado.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < matrizLabirinto.length; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(30);
            gridLabirintoEditado.getRowConstraints().add(rowConst);
        }

        for (int y = 0; y < matrizLabirinto.length; y++) {
            for (int x = 0; x < matrizLabirinto[y].length; x++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(10);
                imageView.setFitHeight(15);

                if (matrizLabirinto[y][x] == 0) {
                    imageView.setImage(new Image("caminho5.jpg"));

                } else if (matrizLabirinto[y][x] == 1) {
                    imageView.setImage(new Image("parede5.jpg"));

                } else if (matrizLabirinto[y][x] == 2) {
                    imageView.setImage(new Image("entrada4.jpg"));

                } else if (matrizLabirinto[y][x] == 3) {
                    imageView.setImage(new Image("saida4.jpg"));

                }
                gridLabirintoEditado.add(imageView, x, y);
            }
        }
    }

    @FXML
    void mostrarResolucaoLabirinto(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Escolha de Resolução");
        alert.setHeaderText("Qual método de resolução você deseja usar?");
        ButtonType bfsButton = new ButtonType("BFS");
        ButtonType dfsButton = new ButtonType("DFS");
        alert.getButtonTypes().setAll(bfsButton, dfsButton);

        Optional<ButtonType> result = alert.showAndWait();
        Labirinto labirinto = new Labirinto(TelaEdicaoLabirintoController.getInstancia().getMatrizLabirintoEditado());
        LinkedList<Integer> caminhoResolucao = null;

        if (result.isPresent() && result.get() == bfsButton) {
            ResolvedorLabirintoBFS resolvedorBFS = new ResolvedorLabirintoBFS(labirinto);
            if (resolvedorBFS.temCaminhoParaSaida()) {
                caminhoResolucao = resolvedorBFS.obterCaminho();
            }
        } else if (result.isPresent() && result.get() == dfsButton) {
            ResolvedorLabirintoDFS resolvedorDFS = new ResolvedorLabirintoDFS(labirinto);
            if (resolvedorDFS.temCaminhoParaSaida()) {
                caminhoResolucao = resolvedorDFS.getCaminhoParaSaida();
            } else {
                System.out.println("Labirinto não tem caminho.");
                System.out.println(labirinto.obterEntrada());
                System.out.println(labirinto.vizinhosConectados(labirinto.obterEntrada()));
                System.out.println(labirinto.vizinhosConectados(labirinto.obterSaida()));
            }
        }
        if (caminhoResolucao != null) {
            for (Integer vertice : caminhoResolucao) {
                int y = vertice / labirinto.obterLargura();
                int x = vertice % labirinto.obterLargura();
                Platform.runLater(() -> {
                    ImageView imageView = (ImageView) gridLabirintoEditado.getChildren().get(y * labirinto.obterLargura() + x);
                    imageView.setImage(new Image("trilha.jpg"));
                });
            }
            gridLabirintoEditado.requestLayout();
        }
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
