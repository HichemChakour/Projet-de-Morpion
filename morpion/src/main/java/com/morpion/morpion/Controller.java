package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Controller implements ContenuChanger {
    @FXML
    private  BorderPane rootLayout;

    @FXML
    private Button btnMenu;

    @FXML
    public void initialize() {
        Image MenuImage = new Image("file:morpion/src/main/resources/image/menu.png");
        ImageView MenuImageView = new ImageView(MenuImage);
        MenuImageView.setFitWidth(35);
        MenuImageView.setFitHeight(35);
        btnMenu.setGraphic(MenuImageView);

        btnMenu.setOnMouseEntered(e -> btnMenu.getScene().setCursor(Cursor.HAND));
        btnMenu.setOnMouseExited(e -> btnMenu.getScene().setCursor(Cursor.DEFAULT));
    }

    @FXML
    private void menu() {
        changerContenu("menu.fxml");
    }

    @FXML
    private void SettingIA() throws IOException {
        FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("Settings.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
        Parent root1 = (Parent) fxmlSettings.load();

        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";

        Stage stage = new Stage();
        stage.setScene(new Scene(root1,280, 230));
        stage.setTitle("Settings");
        stage.show();

    }

    @FXML
    private void ModelsIA() {
        System.out.println("ModelIA");
    }




    @Override
    public void changerContenu(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Node node = loader.load();
            rootLayout.setCenter(node);

            // Si on charge menu.fxml, passez la référence au controller de Menu
            if (fxml.equals("menu.fxml")) {
                Menu menuController = loader.getController();
                menuController.setContenuChanger(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}