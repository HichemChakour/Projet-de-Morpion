package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Settings {

    @FXML
    private void sauvegarder(ActionEvent event) {

        String cheminDuFichier = "com/morpion/morpion/config.txt";

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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
