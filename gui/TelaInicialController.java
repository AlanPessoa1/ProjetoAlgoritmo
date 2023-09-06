package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaInicialController {

    Stage stage;
    Scene scene;

    @FXML
    private Button btnProximaTela1;

    @FXML
    private TextArea txtLabirinto;

    @FXML
    void irTelaEdicaoLabirinto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telaEdicaoLabirinto.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("EDIÇÃO LABIRINTO");
        stage.setScene(scene);
        stage.show();
    }

}
