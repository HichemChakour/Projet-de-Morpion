package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Controller {
    public Button button1;
    @FXML
    private Label welcomeText;

    @FXML
    private void onHelloButtonClick() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1),button1);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }

}