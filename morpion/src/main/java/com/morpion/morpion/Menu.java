package com.morpion.morpion;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.morpion.morpion.ai.MultiLayerPerceptron.load;


public class Menu {
    @FXML
    Button btnHumain;
    @FXML
    Button btnIA;
    @FXML
    Button facile;
    @FXML
    Button moyen;
    @FXML
    Button difficile;


    static String difficulte;

    private ContenuChanger contenuChanger;

    public void setContenuChanger(ContenuChanger contenuChanger) {
        this.contenuChanger = contenuChanger;
    }


    public void initialize() {
        Image HumainImage = new Image("file:morpion/src/main/resources/image/humain.png");
        ImageView HumainImageView = new ImageView(HumainImage);
        HumainImageView.setFitWidth(120);
        HumainImageView.setFitHeight(120);
        btnHumain.setGraphic(HumainImageView);

        Image IAImage = new Image("file:morpion/src/main/resources/image/robot.png");
        ImageView IAImageView = new ImageView(IAImage);
        IAImageView.setFitWidth(120);
        IAImageView.setFitHeight(120);
        btnIA.setGraphic(IAImageView);

        btnHumain.setOnMouseEntered(e -> btnHumain.getScene().setCursor(Cursor.HAND));
        btnHumain.setOnMouseExited(e -> btnHumain.getScene().setCursor(Cursor.DEFAULT));

        btnIA.setOnMouseEntered(e -> btnIA.getScene().setCursor(Cursor.HAND));
        btnIA.setOnMouseExited(e -> btnIA.getScene().setCursor(Cursor.DEFAULT));

    }


    @FXML
    private void Humain() {
        contenuChanger.changerContenu("JeuHumain.fxml");
    }

    @FXML
    private void IA() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), facile);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        FadeTransition fadeTransitionButton = new FadeTransition(Duration.seconds(1), moyen);
        fadeTransitionButton.setFromValue(0);
        fadeTransitionButton.setToValue(1);

        FadeTransition fadeTransitionButton2 = new FadeTransition(Duration.seconds(1), difficile);
        fadeTransitionButton2.setFromValue(0);
        fadeTransitionButton2.setToValue(1);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.1));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.seconds(0.3));
        PauseTransition pauseTransition3 = new PauseTransition(Duration.seconds(0.5));

        pauseTransition.setOnFinished(event -> {
            fadeTransition.play();

        });

        pauseTransition2.setOnFinished(event -> {

            fadeTransitionButton.play();

        });

        pauseTransition3.setOnFinished(event -> {


            fadeTransitionButton2.play();

        });
        pauseTransition3.play();
        pauseTransition2.play();
        pauseTransition.play();

        facile.setOnMouseEntered(e -> facile.getScene().setCursor(Cursor.HAND));
        facile.setOnMouseExited(e -> facile.getScene().setCursor(Cursor.DEFAULT));

        moyen.setOnMouseEntered(e -> moyen.getScene().setCursor(Cursor.HAND));
        moyen.setOnMouseExited(e -> moyen.getScene().setCursor(Cursor.DEFAULT));

        difficile.setOnMouseEntered(e -> difficile.getScene().setCursor(Cursor.HAND));
        difficile.setOnMouseExited(e -> difficile.getScene().setCursor(Cursor.DEFAULT));

        btnIA.setOnAction(e -> {
            TranslateTransition vibration1 = new TranslateTransition(Duration.seconds(0.1), facile);
            vibration1.setFromX(5);
            vibration1.setToX(0);
            vibration1.setCycleCount(5);

            TranslateTransition vibration2 = new TranslateTransition(Duration.seconds(0.1), moyen);
            vibration2.setFromX(5);
            vibration2.setToX(0);
            vibration2.setCycleCount(5);

            TranslateTransition vibration3 = new TranslateTransition(Duration.seconds(0.1), difficile);
            vibration3.setFromX(5);
            vibration3.setToX(0);
            vibration3.setCycleCount(5);

            vibration1.play();
            vibration2.play();
            vibration3.play();
        });


    }





    @FXML
    private void facile() throws IOException {
        boolean exite = false;
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        List<String> list = Files.lines(Paths.get(cheminDuFichier)).collect(Collectors.toList());
        String[] tab;
        tab = list.get(0).split(":");
        Path DossierModel = Paths.get("./morpion/src/main/resources/model");

        File dossier = DossierModel.toFile();
        File[] listeDesFichiers = dossier.listFiles();

        if (listeDesFichiers != null) {
            for (File fichier : listeDesFichiers) {
                if (fichier.isFile()) {
                    if (fichier.getName().equals("model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl")) {
                        exite = true;


                    }

                }
            }
        }
        if (!exite) {
            difficulte = "facile";
            FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("learn.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
            Parent root1 = (Parent) fxmlSettings.load();


            Stage owner = (Stage) btnHumain.getScene().getWindow();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1, 380, 300));
            stage.setTitle("learning...");
            stage.show();

        }
        load("./morpion/src/main/resources/model/"+ "model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl");

    }

    @FXML
    private void moyen() throws IOException {
        boolean exite = false;
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        List<String> list = Files.lines(Paths.get(cheminDuFichier)).collect(Collectors.toList());
        String[] tab;
        tab = list.get(1).split(":");
        Path DossierModel = Paths.get("./morpion/src/main/resources/model");

        File dossier = DossierModel.toFile();
        File[] listeDesFichiers = dossier.listFiles();

        if (listeDesFichiers != null) {
            for (File fichier : listeDesFichiers) {
                if (fichier.isFile()) {
                    if (fichier.getName().equals("model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl")) {
                        exite = true;
                    }

                }
            }
        }
        if (!exite) {
            difficulte = "moyen";
            FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("learn.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
            Parent root1 = (Parent) fxmlSettings.load();


            Stage owner = (Stage) btnHumain.getScene().getWindow();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
            stage.setScene(new Scene(root1, 380, 300));
            stage.setTitle("learning...");
            stage.show();

        }
        load("./morpion/src/main/resources/model/"+ "model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl");

    }

    @FXML
    private void difficile() throws IOException {
        boolean exite = false;
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        List<String> list = Files.lines(Paths.get(cheminDuFichier)).collect(Collectors.toList());
        String[] tab;
        tab = list.get(2).split(":");
        Path DossierModel = Paths.get("./morpion/src/main/resources/model");

        File dossier = DossierModel.toFile();
        File[] listeDesFichiers = dossier.listFiles();

        if (listeDesFichiers != null) {
            for (File fichier : listeDesFichiers) {
                if (fichier.isFile()) {
                    if (fichier.getName().equals("model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl")) {
                        exite = true;

                    }

                }
            }
        }
        if (!exite) {
            difficulte = "difficile";
            FXMLLoader fxmlSettings = new FXMLLoader(Application.class.getResource("learn.fxml"));

            //CenterPane.getChildren().setAll((Pane) fxmlSettings.load());
            Parent root1 = (Parent) fxmlSettings.load();


            Stage owner = (Stage) btnHumain.getScene().getWindow();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
            stage.setScene(new Scene(root1, 380, 300));
            stage.setTitle("learning...");
            stage.show();

        }
        load("./morpion/src/main/resources/model/"+ "model_" + tab[1] + "_" + tab[2] + "_" + tab[3] + ".srl");

    }
}
