package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Controller {
    @FXML
    private Button MenuSettingIA;

    @FXML
    private void SettingIA() {
        System.out.println("SettingIA");
    }

    @FXML
    private void ModelsIA() {
        System.out.println("ModelIA");
    }

}