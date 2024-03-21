package com.morpion.morpion;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Settings {

    @FXML
    private TextField F1;
    @FXML
    private TextField F2;
    @FXML
    private TextField F3;
    @FXML
    private TextField M1;
    @FXML
    private TextField M2;
    @FXML
    private TextField M3;
    @FXML
    private TextField D1;
    @FXML
    private TextField D2;
    @FXML
    private TextField D3;

    @FXML
    private void initialize() throws IOException {
        //On récupère les valeurs du fichier config.txt et on les affiche dans les champs de texte
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        List<String> list = Files.lines(Paths.get(cheminDuFichier)).collect(Collectors.toList());
        String[] tab;
        tab = list.get(0).split(":");

        F1.setText(tab[1]);
        F2.setText(tab[2]);
        F3.setText(tab[3]);
        tab = list.get(1).split(":");

        M1.setText(tab[1]);
        M2.setText(tab[2]);
        M3.setText(tab[3]);
        tab = list.get(2).split(":");

        D1.setText(tab[1]);
        D2.setText(tab[2]);
        D3.setText(tab[3]);
    }
    //Fonction qui permet de sauvegarder les valeurs des champs de texte dans le fichier config.txt et fermer la fenêtre
    /*@FXML
    private void sauvegarder(ActionEvent event) throws IOException {
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        String contenu = "F:" + F1.getText() + ":" + F2.getText() + ":" + F3.getText() + "\n" +
                "M:" + M1.getText() + ":" + M2.getText() + ":" + M3.getText() + "\n" +
                "D:" + D1.getText() + ":" + D2.getText() + ":" + D3.getText();
        Files.write(Paths.get(cheminDuFichier), contenu.getBytes());
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }*/
    //Fonction qui permet de sauvegarder les valeurs des champs de texte dans le fichier config.txt si il s'agit de caractères numériques et fermer la fenêtre
    @FXML
    private void sauvegarder(ActionEvent event) throws IOException {
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        if (F1.getText().matches("[0-9]+") && F2.getText().matches("[0-9]+") && F3.getText().matches("^0*(?:1|0(?:\\.\\d+)?|\\.\\d+)$") &&
                M1.getText().matches("[0-9]+") && M2.getText().matches("[0-9]+") && M3.getText().matches("^0*(?:1|0(?:\\.\\d+)?|\\.\\d+)$") &&
                D1.getText().matches("[0-9]+") && D2.getText().matches("[0-9]+") && D3.getText().matches("^0*(?:1|0(?:\\.\\d+)?|\\.\\d+)$")) {
            String contenu = "F:" + F1.getText() + ":" + F2.getText() + ":" + F3.getText() + "\n" +
                    "M:" + M1.getText() + ":" + M2.getText() + ":" + M3.getText() + "\n" +
                    "D:" + D1.getText() + ":" + D2.getText() + ":" + D3.getText();
            Files.write(Paths.get(cheminDuFichier), contenu.getBytes());
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}