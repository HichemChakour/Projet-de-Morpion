package com.morpion.morpion;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.util.Duration;
import javafx.scene.Cursor;

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

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.5));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.seconds(0.7));
        PauseTransition pauseTransition3 = new PauseTransition(Duration.seconds(0.9));

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

}
