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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    private Pane CenterPane;



    @FXML
    private void SettingIA() throws IOException {
        FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("Settings.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
        Parent root1 = (Parent) fxmlSettings.load();

        String cheminDuFichier = "C:/Users/hchak/IdeaProjects/ProjInterface/morpion/src/main/resources/com/morpion/morpion/config.txt";

        try {
            // Lire toutes les lignes du fichier en une List
            List<String> lignes = Files.readAllLines(Paths.get(cheminDuFichier));

            // Transformer chaque ligne en remplaçant les ":" par rien (ou par le caractère que vous souhaitez)
            List<String> lignesModifiees = lignes.stream()
                    .map(ligne -> ligne.replace(":", ""))
                    .collect(Collectors.toList());

            // Conversion de la List en tableau de String
            String[] tableauDeLignes = lignesModifiees.toArray(new String[0]);

            // Affichage pour vérification
            for (String ligne : tableauDeLignes) {
                System.out.println(ligne);
                System.out.println("test");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }




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