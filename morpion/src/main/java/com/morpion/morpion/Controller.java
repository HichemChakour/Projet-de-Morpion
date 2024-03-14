package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {
    @FXML
    private Pane CenterPane;



    @FXML
    private void SettingIA() throws IOException {
        FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("Settings.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
        Parent root1 = (Parent) fxmlSettings.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1,280, 230));
        stage.setTitle("Settings");
        stage.show();

    }

    @FXML
    private void ModelsIA() {
        System.out.println("ModelIA");
    }

}